import com.google.gson.Gson;
import difflib.Delta;
import difflib.DiffUtils;
import difflib.Patch;
import org.bitbucket.cowwoc.diffmatchpatch.DiffMatchPatch;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class DiffutilsTest {

    @Test
    public void test1() {

        DiffMatchPatch dmp = new DiffMatchPatch();
        LinkedList<DiffMatchPatch.Diff> diff = dmp.diffMain(str1, str2, true);

        diff.get(0);
        System.out.println(new Gson().toJson(diff));
    }

    @Test
    public void test2() throws IOException {
        Patch patch = DiffUtils.diff(stringToLines(str1),stringToLines(str2));
        List<Delta> deltas = patch.getDeltas();
        System.out.println(new Gson().toJson(deltas));

//        DiffUtils.generateUnifiedDiff()

    }
    public List<String> stringToLines(String str) throws IOException {
        List<String> lines = new ArrayList<String>();
        String line;
        BufferedReader in = new BufferedReader(new StringReader(str));
        while ((line = in.readLine()) != null) {
            lines.add(line);
        }
        return lines;
    }

    public static String str1 = "Line 1\n" +
            "Line 2\n" +
            "Line 3\n" +
            "Line 4\n" +
            "Line 5\n" +
            "Line 6\n" +
            "Line 7\n" +
            "Line 8\n" +
            "Line 9\n" +
            "Line 10";
    public static String str2 = "Line 2\n" +
            "Line 3 with changes\n" +
            "Line 4\n" +
            "Line 5 with changes and\n" +
            "a new line\n" +
            "Line 6\n" +
            "new line 6.1\n" +
            "Line 7\n" +
            "Line 8\n" +
            "Line 9\n" +
            "Line 10 with changes";
}
