/**
 * kd比平均を扱うクラス
 * @author pukusyou
 */
public class ValorantAve{

	private ValorantScore valorantScore;
	private String name, team;
	private double kdAve;

	/**
	 * コンストラクタ
	 * @param name プレイヤー名
	 * @param team チーム名
	 * @param killList キル数一覧
	 * @param deathList デス数一覧
	 */
	public ValorantAve(String name, String team, int[] killList, int[] deathList) {
		this.name = name;
		this.team = team;
		valorantScore = new ValorantScore(name, team);
		setKillScore(killList);
		setDeathScore(deathList);
		valorantScore.kdCalc();
		kdAve = calcKDAve();
	}

	/**
	 * 引数をvalorantScoreのsetKillListメソッドを用いて代入
	 * @param killList 試合ごとのキル数
	 */
	public void setKillScore(int[] killList){
		for (int i = 0; i < killList.length; i++) {
			valorantScore.setKillList(killList[i]);
		}
	}

	/**
	 * 引数をvalorantScoreのsetDeathListメソッドを用いて代入
	 * @param deathList 試合ごとのデス数
	 */
	public void setDeathScore(int[] deathList){
		for (int i = 0; i < deathList.length; i++) {
			valorantScore.setDeathList(deathList[i]);
		}
	}

	/**
	 * kd比の平均を計算し、返します
	 * @return 直近数試合のkd比の平均
	 */
	public double calcKDAve() {
		double sum = 0;
		for (Double kd : valorantScore.getKDList()) {
			sum += kd;
		}
		return sum / valorantScore.getKDList().size();
	}

	/**
	 * kd比の平均を表示します
	 */
	public void showKDAve() {
		System.out.printf("%sの最近%d試合の平均KDは%.2fです\n", name, valorantScore.getKDList().size(), kdAve);
	}

	/**
	 * kdAveのゲッター
	 * @return kdAve
	 */
	public double getKDAvg() {
		return kdAve;
	}

	/**
	 * teamのゲッター
	 * @return team
	 */
	public String getTeam() {
		return team;
	}

	/**
	 * nameのゲッター
	 * @return name
	 */
	public String getName() {
		return name;
	}
}
