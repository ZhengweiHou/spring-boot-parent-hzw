package com.hzw.grpc.fram.message;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @ClassName AicGrpcRequest
 * @Description TODO
 * @Author houzw
 * @Date 2023/7/25
 **/
public class AicGrpcRequest {
    private static final long serialVersionUID = -9106471041209936049L;

    /**
     * Interfase name
     */
    private String interfaceName;
    /**
     * Method name
     */
    private String             methodName;

    /**
     * Argument type strings of method
     */
    private String[]           methodArgSigs;

    /**
     * Argument values of method
     */
    private Object[] methodArgs;

    /**
     * SerializerCode of methodArgs
     */
    private Byte serializerCode;

    /**
     * Extensional properties of request
     */
    private Map<String, Object> requestProps;


    public String getInterfaceName() {
        return interfaceName;
    }
    /**
     * Gets method name.
     *
     * @return the method name
     */
    public String getMethodName() {
        return methodName;
    }

    /**
     * Get method args object [ ].
     *
     * @return the object [ ]
     */
    public Object[] getMethodArgs() {
        return methodArgs;
    }

    /**
     * Get method arg sigs string [ ].
     *
     * @return the string [ ]
     */
    public String[] getMethodArgSigs() {
        return methodArgSigs;
    }

    public Byte getSerializerCode() {
        return serializerCode;
    }

    /**
     * Sets method name.
     *
     * @param methodName the method name
     */
    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }


    public void setInterfaceName(String interfaceName) {
        this.interfaceName = interfaceName;
    }
    /**
     * Sets method args.
     *
     * @param methodArgs the method args
     */
    public void setMethodArgs(Object[] methodArgs) {
        this.methodArgs = methodArgs;
    }

    /**
     * Sets method arg sigs.
     *
     * @param methodArgSigs the method arg sigs
     */
    public void setMethodArgSigs(String[] methodArgSigs) {
        this.methodArgSigs = methodArgSigs;
    }

    public void setSerializerCode(Byte serializerCode) {
        this.serializerCode = serializerCode;
    }
    /**
     * Gets request prop.
     *
     * @param key the key
     * @return request prop
     */
    public Object getRequestProp(String key) {
        return requestProps != null ? requestProps.get(key) : null;
    }

    /**
     * Add request prop.
     *
     * @param key   the key
     * @param value the value
     */
    public void addRequestProp(String key, Object value) {
        if (key == null || value == null) {
            return;
        }
        if (requestProps == null) {
            requestProps = new HashMap<String, Object>(16);
        }
        requestProps.put(key, value);
    }

    /**
     * Remove request prop.
     *
     * @param key the key
     */
    public void removeRequestProp(String key) {
        if (key == null) {
            return;
        }
        if (requestProps != null) {
            requestProps.remove(key);
        }
    }

    /**
     * Add request props.
     *
     * @param map the map
     */
    public void addRequestProps(Map<String, Object> map) {
        if (map == null || map.isEmpty()) {
            return;
        }
        if (requestProps == null) {
            requestProps = new HashMap<String, Object>(16);
        }
        requestProps.putAll(map);
    }

    /**
     * Gets request props.
     *
     * @return the request props
     */
    public Map<String, Object> getRequestProps() {
        return requestProps;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(128);
        sb.append("AicGrpcRequest[");
        sb.append("Interface=").append(interfaceName).append(", ");
        sb.append("Method=").append(methodName).append(", ");
        sb.append("Parameters=[");
        if (null != methodArgs) {
            for (Object arg : methodArgs) {
                sb.append(arg).append(",");
            }
            sb.deleteCharAt(sb.length() - 1);
        }
        sb.append("]]");
        return sb.toString();
    }
}
