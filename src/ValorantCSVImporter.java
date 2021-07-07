import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import jdk.internal.org.jline.reader.impl.KillRing;

public class ValorantCSVImporter {

    //ファイル中身 名前,元チーム,試合数,キル数,...,デス数,...,...

    private Path path;
    private ArrayList<ValorantAve> memberData;
    private ArrayList<String> allData;

    public ValorantCSVImporter(String fileName){
        allData = new ArrayList<>();
        memberData = new ArrayList<>();
        path = Paths.get(fileName);
        try(BufferedReader reader = Files.newBufferedReader(path)) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] str = line.split(",");
                for (int i = 0; i < str.length; i++) {
                    allData.add(str[i]);
                }
            }
        } catch (Exception e) {
            // エラー表示
        }
    }

    public int[] getkillList(String name){
        int[] killList;
        for (int i = 0; i < allData.size(); i++) {
            if (name.equals(allData.get(i))) {
                //試合数分のkillList配列を宣言
                killList = new int[Integer.parseInt(allData.get(i+2))];
                for (int j : killList) {
                    j = Integer.parseInt(allData.get(i++));
                }
                return killList;
            }
        }
        //エラー時
        System.out.println("killListを作成できませんでした");
        return null;
    }

    public int[] getDeathList(String name){
        int[] deathList;
        for (int i = 0; i < allData.size(); i++) {
            if (name.equals(allData.get(i))) {
                //試合数分のdeathList配列を宣言
                deathList = new int[Integer.parseInt(allData.get(i+2))];
                for (int j : deathList) {
                    j = Integer.parseInt(allData.get(i++));
                }
                return deathList;
            }
        }
        //エラー時
        System.out.println("deathListを作成できませんでした");
        return null;
    }

    public int getNumOfGame(String name){
        int result;
        for (String string : allData) {
            if(string.equals(name)){
                result = Integer.parseInt(string);
            }
        }
        return result;
    }

    public void MemberDataImputer(){
        int name, team, numOfGame;
        memberData.add(new ValorantAve(allData.get(name), allData.get(team), getkillList(/*名前*/), getDeathList(/*名前*/)));
    }
}