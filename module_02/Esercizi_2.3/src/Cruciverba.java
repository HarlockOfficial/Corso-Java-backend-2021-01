public class Cruciverba {
    public static void main(String[] args) {
        char[][] matr = {{'a','b','c', 'j'},
                         {'d','e','f', 'k'},
                         {'g','h','i', 'l'}};
        System.out.println(isPresent(matr, "bd"));
    }
    public static boolean isPresent(char[][] matr, String s) {
        boolean result = false;
        for (int i = 0; !result && i < matr.length; ++i) {
            result = checkRow(matr[i], s);
            result |= checkRow(invert(matr[i]), s);
        }
        for (int i = 0; !result && i < matr[0].length; ++i) {
            char[] col = getColumn(matr, i);
            result = checkRow(col, s);
            result |= checkRow(invert(col), s);
        }
        for (int i = 0; !result && i < matr.length + matr[0].length - 1; ++i) {
            char[] diag = getDiag(matr, i);
            result = checkRow(diag, s);
            result |= checkRow(invert(diag), s);
            diag = getRevDiag(matr, i);
            result |= checkRow(diag, s);
            result |= checkRow(invert(diag), s);
        }
        return result;
    }

    private static boolean checkRow(char[] arr, String s) {
        if(arr.length<s.length()){
            return false;
        }
        int j = 0;
        for(int i = 0;j<s.length() && i<arr.length; ++i){
            if(arr[i] == s.charAt(j)){
                ++j;
            }else if(j>0) {
                j = 0;
                --i;
            }
        }
        return j == s.length();
    }

    private static char[] getColumn(char[][] matr, int column_index) {
        char[] out = new char[matr.length];
        for (int i = 0; i < out.length; ++i) {
            out[i] = matr[i][column_index];
        }
        return out;
    }

    private static char[] getDiag(char[][] matr, int diagonal_index) {
        int row = matr.length-1;
        int col = matr[0].length-1;

        char [] c = new char[]{};

        for(int i = 0, j = diagonal_index; i<=row && j>=0; ++i, --j){
            if(j>col){
                continue;
            }
            char tmp = matr[row-i][j];
           c = append(c, tmp, c.length) ;
        }
        return c;
    }

    private static char[] getRevDiag(char[][] matr, int diagonal_index) {
        int row = matr.length-1;
        int col = matr[0].length-1;

        char [] c = new char[]{};

        for(int i = diagonal_index, j = 0; i>=0 && j<=col; --i, ++j){
            if(i>row){
                continue;
            }
            char tmp = matr[row-i][col-j];
            c = append(c, tmp, c.length) ;
        }
        return c;
    }

    private static char[] append(char[] in, char c, int n) {
        char[] out = new char[n + 1];
        if (n >= 0) System.arraycopy(in, 0, out, 1, n);
        out[0] = c;
        return out;
    }

    private static char[] invert(char[] in){
        for(int i = 0; i<in.length/2; ++i){
            char tmp = in[i];
            in[i] = in[in.length-i-1];
            in[in.length-i-1] = tmp;
        }
        return in;
    }
}