package zyx.func.functions;

public class Letters {
    private int[] letters = new int[26];
    private float[] rate = new float[26];
    private LetterHandler letterHandler;
    public void cout(String filepath){
        letterHandler.cout(filepath);
    }
}
