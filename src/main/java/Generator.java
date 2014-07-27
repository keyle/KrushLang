public class Generator {

    public static final String CLASSCODE = "public class %s {";

    public static void generate(Parser.ClassDef classDef) {

        String fileContent = "";
        fileContent += String.format(CLASSCODE, classDef.name);

        System.out.println(fileContent);
    }

}
