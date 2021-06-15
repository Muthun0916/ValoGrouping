import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Scanner;

/**
 * すべての出場プレイヤーのデータを保持し、チーム分けやマップ選択を行うクラス
 * 数時間で作ったからすごい汚いプログラムやけど許して(TAT)ぴえん
 * @author pukusyou
 */

public class ValorantAllMember {

	/*各プレイヤーの情報を保持する参照型変数*/
	private ValorantAve pukusyouAve, selraAve, mutsuttoAve, gandouAve, aiuAve, momoAve, furamuAve, mattyaajiAve,
		kisuAve, qaqAve, ojiAve,
		kishiAve;

	/*2チームのキルデス比の差の最大値 */
	private double kdGap = 1.5;

	/*マップ一覧が格納*/
	private ArrayList<String> valoMap = new ArrayList<String>();

	/*元チームが何人いるか保持する変数*/
	int pTeam1 = 0, kTeam1 = 0, pTeam2 = 0, kTeam2 = 0;

	private ArrayList<ValorantAve> kouJunArrayList = new ArrayList<ValorantAve>();
	private ArrayList<ValorantAve> team1 = new ArrayList<ValorantAve>();
	private ArrayList<ValorantAve> team2 = new ArrayList<ValorantAve>();
	Map<Integer, ValorantAve> map;

	/*各プレイヤーの情報を持つ配列*/
	int[] pukusyouKill = { 20, 13, 18, 19, 6, 9, 13, 14, 16, 9, 18, 17, 18, 9, 5, 32, 21 };//カスタム2戦追加
	int[] pukusyouDeath = { 11, 6, 8, 16, 15, 19, 17, 13, 15, 6, 11, 18, 18, 15, 13, 16, 20 };
	int[] selraKill = { 25, 18, 18, 20, 7, 8, 14, 7, 25, 22, 18, 10, 6, 21, 21, 28, 18 };//カスタム2戦追加
	int[] selraDeath = { 13, 5, 19, 7, 16, 13, 15, 10, 11, 16, 16, 15, 14, 15, 8, 16, 21 };
	int[] mutsuttochanKill = { 12, 14, 14, 7, 17, 8, 20, 27, 13, 14, 3, 18, 12 };
	int[] mutsuttochanDeath = { 18, 10, 17, 14, 19, 17, 19, 22, 21, 16, 21, 17, 11, 17 };
	int[] supergandouKill = { 8, 9, 17, 8, 12, 4, 3, 12, 14, 16, 19, 9, 12, 12 };
	int[] supergandouDeath = { 12, 6, 13, 17, 16, 16, 16, 17, 19, 16, 19, 18, 13, 13 };
	int[] aiuKill = { 5, 4, 8, 6, 13, 10, 9, 11, 9, 7, 9, 13, 9, 6, 1 };
	int[] aiuDeath = { 13, 4, 7, 17, 13, 15, 18, 18, 14, 14, 23, 19, 16, 2, 5 };
	int[] momoKill = { 15, 10, 4, 11, 8, 13, 10, 7, 12, 5, 10, 11, 11, 13, 21 };
	int[] momoDeath = { 17, 16, 14, 12, 12, 26, 18, 15, 12, 15, 20, 18, 15, 14, 16 };

	int[] furamu32Kill = { 13, 7, 7, 8, 9, 11, 6, 12, 11, 14, 3, 6, 10 };
	int[] furamu32Death = { 13, 17, 17, 12, 10, 6, 18, 18, 9, 18, 5, 17, 12 };
	int[] mattyaajiKill = { 6, 8, 16, 9, 4, 10, 6, 24, 5, 13, 13, 9, 24, 13, 23, 27 };//カスタム2戦追加
	int[] mattyaajiDeath = { 2, 14, 12, 14, 6, 8, 19, 10, 8, 12, 12, 18, 17, 14, 21, 20 };//カスタム2戦追加
	int[] kisuKill = { 14, 16, 1, 12, 19, 3, 16, 12, 17, 22, 18 }; //カスタム1
	int[] kisuDeath = { 16, 17, 17, 13, 13, 15, 19, 14, 17, 18, 14 }; //カスタム1
	int[] qaqKill = { 32, 24 };
	int[] qaqDeath = { 22, 25 };
	int[] kishiKill = { 3, 3 };
	int[] kishiDeath = { 19, 20 };
	int[] ojiKill = { 35 };
	int[] ojiDeath = { 15 };

	/**
	 * コンストラクタ
	 * それぞれオブジェクト生成や追加
	 */
	public ValorantAllMember() {
		String team = "P";
		pukusyouAve = new ValorantAve("pukusyou", team, pukusyouKill, pukusyouDeath);
		selraAve = new ValorantAve("selra", team, selraKill, selraDeath);
		mutsuttoAve = new ValorantAve("Mutsuttochan", team, mutsuttochanKill, mutsuttochanDeath);
		gandouAve = new ValorantAve("gandou", team, supergandouKill, supergandouDeath);
		aiuAve = new ValorantAve("aiu", team, aiuKill, aiuDeath);
		momoAve = new ValorantAve("ぬこもも", team, momoKill, momoDeath);

		team = "K";
		furamuAve = new ValorantAve("furamu32", team, furamu32Kill, furamu32Death);
		mattyaajiAve = new ValorantAve("mattyaaji", team, mattyaajiKill, mattyaajiDeath);
		kisuAve = new ValorantAve("きーすぅ", team, kisuKill, kisuDeath);
		qaqAve = new ValorantAve("QAQ", team, qaqKill, qaqDeath);
		kishiAve = new ValorantAve("岸ゆうや", team, kishiKill, kishiDeath);
		ojiAve = new ValorantAve("おじちょぴ", team, ojiKill, ojiDeath);
		kouJun();

		valoMap.add("ブリーズ");
		valoMap.add("アイスボックス");
		valoMap.add("バインド");
		valoMap.add("ヘイヴン");
		valoMap.add("スプリット");
		valoMap.add("アセント");

	}

	/**
	 * 全員のkd比を表示します
	 */
	public void showAllMember() {
		for (ValorantAve ave : kouJunArrayList) {
			ave.showKDAve();
		}
	}

	/**
	 * kd比の降順にします
	 */
	public void kouJun() {
		Map<ValorantAve, Double> map = new HashMap<ValorantAve, Double>();
		map.put(pukusyouAve, pukusyouAve.getKDAvg());
		map.put(selraAve, selraAve.getKDAvg());
		map.put(mutsuttoAve, mutsuttoAve.getKDAvg());
		map.put(gandouAve, gandouAve.getKDAvg());
		map.put(aiuAve, aiuAve.getKDAvg());
		map.put(ojiAve, ojiAve.getKDAvg());
		map.put(momoAve, momoAve.getKDAvg());
		map.put(furamuAve, furamuAve.getKDAvg());
		map.put(mattyaajiAve, mattyaajiAve.getKDAvg());
		map.put(kisuAve, kisuAve.getKDAvg());
		map.put(qaqAve, qaqAve.getKDAvg());

		List<Entry<ValorantAve, Double>> list = new ArrayList<>(map.entrySet());
		Collections.sort(list, new Comparator<Entry<ValorantAve, Double>>() {
			//compareを使用して値を比較する
			public int compare(Entry<ValorantAve, Double> obj1, Entry<ValorantAve, Double> obj2) {
				
				return obj2.getValue().compareTo(obj1.getValue());
			}
		});

		for (Entry<ValorantAve, Double> entry : list) {
			kouJunArrayList.add(entry.getKey());
		}
	}

	/**
	 * ランダムにチームを分けます
	 */
	public void grouping() {
		Random rand = new Random();
		map = new HashMap<Integer, ValorantAve>();
		int i = 0;
		map.put(i++, pukusyouAve);
		map.put(i++, selraAve);
		map.put(i++, mutsuttoAve);
		map.put(i++, ojiAve);
		map.put(i++, aiuAve);
		map.put(i++, momoAve);
		map.put(i++, furamuAve);
		map.put(i++, mattyaajiAve);
		map.put(i++, kisuAve);
		map.put(i++, qaqAve);

		while (team1.size() != 5) {
			int num = rand.nextInt(map.size());
			if (!(map.containsKey(num))) {
				continue;
			} else {
				team1.add(map.get(num));
				map.remove(num);
			}

		}
		for (ValorantAve ave : map.values()) {
			team2.add(ave);
		}

	}

	/**
	 * 2チーム表示します
	 */
	public void showTeam() {
		System.out.println("team1:");
		for (ValorantAve team : team1) {
			System.out.println(team.getName());
		}

		System.out.println();

		System.out.println("team2:");
		for (ValorantAve ave : map.values()) {
			System.out.println(ave.getName());
		}

	}

	/**
	 * チームの合計kd比を返します
	 * @param team チーム
	 * @return 合計kd
	 */
	public double getSumKD(ArrayList<ValorantAve> team) {
		double sum = 0;
		for (ValorantAve num : team) {
			sum += num.getKDAvg();
		}
		return sum;
	}

	/**
	 * team1に属しているメンバーのオブジェクトを返します
	 * @return team1のオブジェクトのリスト
	 */
	public ArrayList<ValorantAve> getTeam1() {
		return team1;
	}

	/**
	 * team2に属しているメンバーのオブジェクトを返します
	 * @return team2のオブジェクトのリスト
	 */
	public ArrayList<ValorantAve> getTeam2() {
		return team2;
	}

	/**
	 * チームごとのkd比の平均の差を返します
	 */
	public double getKDGap(ValorantAllMember valorantAllMember) {
		kdGap = Math.abs(valorantAllMember.getSumKD(valorantAllMember.getTeam1()) / 5 -
				valorantAllMember.getSumKD(valorantAllMember.getTeam2()) / 5);
		return kdGap;
	}

	/**
	 * チームに前に属していたチームが何人いるか数えます
	 */
	public void numOfTeam() {
		pTeam1 = 0;
		kTeam1 = 0;
		pTeam2 = 0;
		kTeam2 = 0;
		for (ValorantAve team : team1) {
			if (team.getTeam().equals("P")) {
				pTeam1++;
			} else {
				kTeam1++;
			}
		}
		for (ValorantAve team : team2) {
			if (team.getTeam().equals("P")) {
				pTeam2++;
			} else {
				kTeam2++;
			}
		}
	}

	/**
	 * 前に属していたチームの人が多すぎる場合やkdに差がありすぎる場合もう一度チームを分け直します
	 * @param valorantAllMember 自クラスのオブジェクト
	 * @param gapMax kd比の差の最大値
	 */
	public void groupingRepeat(ValorantAllMember valorantAllMember, double gapMax) {
		valorantAllMember.grouping();

		while (valorantAllMember.getKDGap(valorantAllMember) >= gapMax || pTeam1 >= 4 || pTeam2 >= 4 || kTeam1 >= 4
				|| kTeam2 >= 4) {
			team1.clear();
			team2.clear();
			valorantAllMember.grouping();
			numOfTeam();
		}
	}

	/**
	 * valoMapのゲッター
	 * @return valoMap
	 */
	public ArrayList<String> getValoMap() {
		return valoMap;
	}

	/**
	 * banMapをリストから削除し、その後のリストを返します
	 * @param banMap1 banするマップの1つ目
	 * @param banMap2 banするマップの2つ目
	 * @return banMapをリストから削除した後のリスト
	 */
	public String selectMap(String banMap1, String banMap2) {
		Random random = new Random();
		for (int i = 0; i < valoMap.size(); i++) {
			if (valoMap.get(i).equals(banMap1)) {
				valoMap.remove(i);
			} else if (valoMap.get(i).equals(banMap2)) {
				valoMap.remove(i);
			}
		}
		int num = random.nextInt(valoMap.size());
		return valoMap.get(num);

	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		ValorantAllMember valorantAllMember = new ValorantAllMember();
		//		valorantAllMember.showAllMember();
		//		System.out.println();
		valorantAllMember.groupingRepeat(valorantAllMember, 0.5);
		valorantAllMember.showTeam();
		System.out.println();
		System.out.println("team1のKD平均は");
		System.out.printf("%.2f\n", valorantAllMember.getSumKD(valorantAllMember.getTeam1()) / 5);
		System.out.println("team2のKD平均は");
		System.out.printf("%.2f\n", valorantAllMember.getSumKD(valorantAllMember.getTeam2()) / 5);
		System.out.println("2チームのKD平均の差は");
		System.out.printf("%.2f\n", valorantAllMember.getKDGap(valorantAllMember));
		String banMap1 = "", banMap2 = "";

		System.out.println();
		if (valorantAllMember.getSumKD(valorantAllMember.getTeam1())
				/ 5 < valorantAllMember.getSumKD(valorantAllMember.getTeam2()) / 5) {
			System.out.print("チーム2がBANするマップを入力してください:");
			banMap1 = scanner.next();
			System.out.print("チーム1がBANするマップを入力してください:");
			banMap2 = scanner.next();
		} else {
			System.out.print("チーム1がBANするマップを入力してください:");
			banMap1 = scanner.next();
			System.out.print("チーム2がBANするマップを入力してください:");
			banMap2 = scanner.next();
		}

		System.out.print("選択されたマップは");
		System.err.print(valorantAllMember.selectMap(banMap1, banMap2));
		System.out.print("になりました");

		scanner.close();
	}

}
