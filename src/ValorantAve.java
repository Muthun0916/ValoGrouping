public class ValorantAve{

	private ValorantScore valorantScore;
	private String name, team;
	private double kdAve;

	public void setKillScore(int[] killList){
		for (int i = 0; i < killList.length; i++) {
			valorantScore.setKillList(killList[i]);
		}
	}

	public void setDeathScore(int[] deathList){
		for (int i = 0; i < deathList.length; i++) {
			valorantScore.setDeathList(deathList[i]);
		}
	}

	public double calcKDAve() {
		double sum = 0;
		for (Double kd : valorantScore.getKDList()) {
			sum += kd;
		}
		return sum / valorantScore.getKDList().size();
	}

	public ValorantAve(String name, String team, int[] killList, int[] deathList) {
		this.name = name;
		this.team = team;
		valorantScore = new ValorantScore(name, team);
		setKillScore(killList);
		setDeathScore(deathList);
		valorantScore.kdCalc();
		kdAve = calcKDAve();
	}

	public void showKDAve() {
		System.out.printf("%sの最近%d試合の平均KDは%.2fです\n", name, valorantScore.getKDList().size(), kdAve);
	}

	public double getKDAvg() {
		return kdAve;
	}

	public String getTeam() {
		return team;
	}

	public String getName() {
		return name;
	}

//	public int compareTo(ValorantAve o) {
//		if(this.kdAve < o.kdAve) {
//			return -1;
//		}
//		if(this.kdAve > o.kdAve) {
//			return 1;
//		}
//		return 0;
//	}
}
