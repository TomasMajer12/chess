package cz.cvut.fel.pjv.ChessLoader;



import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ChessPgnSaver {

    /**
     * Save game in PGN format
     * @param moves
     * @param file
     * @throws IOException
     */
    public void savePGNdata(List<String> moves,File file) throws IOException {
        BufferedWriter out = null;
        try {
            out = new BufferedWriter(new FileWriter(file));
            writeHeaders(out);
            out.write("\n");

            int move = 1;
            int count = 0;

            for (String s: moves) {
                if(count == 2){
                    count=0;
                }
                if(count == 0){
                    out.write(move + ". ");
                    move++;
                }

                out.write(s + " ");
                count++;
            }
            out.write("\n");

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                out.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    /**
     * Writing headers
     * @param out
     * @throws IOException
     */
    public void writeHeaders(BufferedWriter out) throws IOException {
        out.write("Date \"" + getCurrentTimeStamp() + "\"]\n");
        out.write("City \"" + "Prague" + "\"]\n");
        out.write("White \"" + "White Player" + "\"]\n");
        out.write("Black \"" + "Black Player" + "\"]\n");
    }

    /**
     * get current date
     * @return
     */
    public static String getCurrentTimeStamp() {
        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");//dd/MM/yyyy
        Date now = new Date();
        String strDate = sdfDate.format(now);
        return strDate;
    }
}
