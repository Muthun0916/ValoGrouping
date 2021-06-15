import java.util.ArrayList;

/**
 * 一人の情報を保持するクラス
 * @author pukusyou
 */
public class ValorantScore {

	private String name, team;
	private ArrayList<Integer> killList;
	private ArrayList<Integer> deathList;
	private ArrayList<Integer> assistList;
	private ArrayList<Double> kdList;
	private ArrayList<Double> kdaList;

	/**
	 * コンストラクタ
	 * @param name プレイヤーの名前
	 * @param team 元所属していたチーム
	 */
	public ValorantScore(String name, String team) {
		this.name = name;
		this.team = team;
		killList = new ArrayList<Integer>();
		deathList = new ArrayList<Integer>();
		assistList = new ArrayList<Integer>();
		kdList = new ArrayList<Double>();
		kdaList = new ArrayList<Double>();
	}

	/**
	 * 引数をArrayListのKillListに追加します
	 * @param kill キル数
	 */
	public void setKillList(int kill) {
		killList.add(kill);
	}

	/**
	 * 
	 * @param death
	 */
	public void setDeathList(int death) {
		deathList.add(death);
	}

	public void setAssistList(int assist) {
		assistList.add(assist);
	}

	public void kdCalc() {
		ValorantMember valorantMember;
		for (int i = 0; i < killList.size(); i++) {
			double kd;
			valorantMember = new ValorantMember(this.name, killList.get(i), deathList.get(i));
			valorantMember.KDCalculator();
			kd = valorantMember.getKD();
			kdList.add(kd);
		}
	}

	public void kdaCalc() {
		ValorantMember valorantMember;
		for (int i = 0; i < killList.size(); i++) {
			double kda;
			valorantMember = new ValorantMember(this.name, killList.get(i), deathList.get(i), assistList.get(i));
			valorantMember.KDACalculator();
			kda = valorantMember.getKDA();
			kdaList.add(kda);
		}
	}

	public ArrayList<Double> getKDList(){
		return kdList;
	}

	public ArrayList<Double> getKDAList(){
		return kdaList;
	}

	public ArrayList<Integer> getKillList() {
		return killList;
	}

	public String getTeam() {
		return team;
	}
}
