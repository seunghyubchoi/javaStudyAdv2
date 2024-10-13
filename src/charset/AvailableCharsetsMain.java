package charset;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Set;
import java.util.SortedMap;

public class AvailableCharsetsMain {
    public static void main(String[] args) {
        SortedMap<String, Charset> charSets = Charset.availableCharsets();
        for (String charsetName : charSets.keySet()) {
            System.out.println("charsetName: " + charsetName);
        }

        System.out.println("=============");
        // 문자로 조회, 대소문자 구분 x

        Charset charset1 = Charset.forName("MS949");
        System.out.println("ms949: " + charset1.name());

        // 별칭 조회
        Set<String> aliases = charset1.aliases();
        for (String alias : aliases) {
            System.out.println("alias: " + alias);
        }

        // UTF-8 문자로 조회
        Charset charset2 = Charset.forName("UTF-8");
        System.out.println("UTF-8: " + charset2.name());
        //상수로 조회
        //StandardCharsets.UTF_8

        Charset charset = Charset.defaultCharset();
        System.out.println("Default: " + charset.name());
    }
}
