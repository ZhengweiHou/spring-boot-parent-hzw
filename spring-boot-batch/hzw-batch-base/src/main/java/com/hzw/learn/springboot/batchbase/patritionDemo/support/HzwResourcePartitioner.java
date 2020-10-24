package com.hzw.learn.springboot.batchbase.patritionDemo.support;

import jdk.nashorn.internal.objects.annotations.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.partition.support.Partitioner;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.core.io.FileSystemResource;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.Map;
import java.util.TreeMap;

public class HzwResourcePartitioner implements Partitioner {

    private Logger log = LoggerFactory.getLogger(getClass());

    FileSystemResource resource;

    int lineBytesLength = 10;       // 每行记录字节长度，作为文件记录拆分依据

    @Override
    public Map<String, ExecutionContext> partition(int gridSize) {
        log.info("want to partition file [{}], gridSizw [{}]", resource.getFile(), gridSize);

//      =====1=====
//        try (BufferedReader reader = Files.newBufferedReader(Paths.get(resource.getPath()), StandardCharsets.UTF_8)) {
//            for (;;) {
//                String line = reader.readLine();
//                if (line == null)
//                    break;
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

//        ====2====
//        try(
//            BufferedReader br = new BufferedReader(new FileReader(resource.getFile()))
//        ) {
//            br.readLine();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

//        ====3====
        Map<String, ExecutionContext> result = new TreeMap<>();     // 用于存放拆分后的ExecutionContext集合

        try(
                FileChannel inChannel = new FileInputStream(resource.getFile()).getChannel();
        ) {
            long size = inChannel.size();
            int records = (int) (size / lineBytesLength);       // 10 为每行数据大小，这里计算总行数
            int parttionSize = (int) (records*1.0 / gridSize + 0.9999999);   // 计算每个分片的记录数 +1 是为了防止分片过小。 后续分片实际上根据parttionSize来进行分片

            log.info("fileSizw:[{}]，records:[{}]，parttionSize:[{}]", size, records, parttionSize);

            // 创建拆分的临时文件夹
            File partitionDirectory = new File(resource.getFile().getParent() + File.separator + resource.getFilename() + ".parttions");
            if( partitionDirectory.exists()){
                log.info("partitionDirectory exists, will be delete and create new directory:{}", partitionDirectory.getPath());
                partitionDirectory.delete();
            }
            partitionDirectory.mkdir();

            // 拆分源文件
            for (int i=1,restLines = records; restLines > 0; i++,restLines -= parttionSize){
                // 构造ExecutionContext
                int skip = (i-1) * parttionSize;
                ExecutionContext executionContext = new ExecutionContext();
                executionContext.putInt("skip", skip);
                executionContext.putInt("parttionSize", parttionSize);
                executionContext.putInt("partIndex",i);
                executionContext.putString("partFilePath",partitionDirectory.getAbsolutePath() + File.separator + resource.getFilename() + "_" + i);
                result.put("part"+i, executionContext);

                // 创建拆分文件
                File splitFile =  new File(partitionDirectory.getAbsolutePath() + File.separator + resource.getFilename() + "_" + i );
                if (splitFile.exists())
                    splitFile.delete();
                splitFile.createNewFile();

                FileChannel splitFileOutchannel = null;
                try{
                    // 输出拆分文件内容
                    splitFileOutchannel = new FileOutputStream(splitFile).getChannel();
                    inChannel.transferTo(skip * lineBytesLength, parttionSize * lineBytesLength, splitFileOutchannel);
                    log.info("output to splitfile:[{}]", splitFile.getAbsolutePath());
                    splitFileOutchannel.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }finally {
                    if(null != splitFileOutchannel) splitFileOutchannel.close();
                }

            }

        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }

        log.info("parttion over，splitfile size [{}]", result.size());
        return result;
    }


    public void setResource(FileSystemResource resource) {
        this.resource = resource;
    }

    public void setLineBytesLength(int lineBytesLength) {
        this.lineBytesLength = lineBytesLength;
    }
}
