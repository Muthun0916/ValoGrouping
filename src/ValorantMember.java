/**
 * メンバーのkd, kda比を計算するクラス
 * @author pukusyou
 */
public class ValorantMember {

	private String name;
	private int kill, death, assist;
	private double kd, kda;

	/**
	 * kda比を求める場合のコンストラクタ
	 * @param name プレイヤー名
	 * @param kill キル数
	 * @param death デス数
	 * @param assist アシスト数
	 */
	public ValorantMember(String name, int kill, int death, int assist) {
		this.name = name;
		this.kill = kill;
		this.death = death;
		this.assist = assist;
	}

	/**
	 * kd比を求める場合のコンストラクタ
	 * @param name プレイヤー名
	 * @param kill キル数
	 * @param death デス数
	 */
	public ValorantMember(String name, int kill, int death) {
		this.name = name;
		this.kill = kill;
		this.death = death;
	}

	/**
	 * kd比を計算します
	 */
	public void KDCalculator() {
		if (death == 0) {
			kd = kill / 1;
		}else {
			kd = (double)kill / death;
		}
	}

	/**
	 * kda比を計算します
	 */
	public void KDACalculator() {
		if (death == 0) {
			kda = (kill + assist) / 1;
		}else {
			kda = (kill + assist) / (double)death;
		}
	}

	/**
	 * nameのゲッター
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * kdのゲッター
	 * @return kd
	 */
	public double getKD() {
		return kd;
	}

	/**
	 * kdaのゲッター
	 * @return kda
	 */
	public double getKDA() {
		return kda;
	}
}
