/**
 * kd, kda比を計算するクラス
 * @author kitagawashoma
 */
public class ValorantMember {

	private String name;
	private int kill, death, assist;
	private double kd, kda;

	public ValorantMember(String name, int kill, int death, int assist) {
		this.name = name;
		this.kill = kill;
		this.death = death;
		this.assist = assist;
	}

	public ValorantMember(String name, int kill, int death) {
		this.name = name;
		this.kill = kill;
		this.death = death;
	}

	public void KDCalculator() {
		if (death == 0) {
			kd = kill / 1;
		}else {
			kd = (double)kill / death;
		}
	}

	public void KDACalculator() {
		if (death == 0) {
			kda = (kill + assist) / 1;
		}else {
			kda = (kill + assist) / (double)death;
		}
	}

	public String getName() {
		return name;
	}

	public double getKD() {
		return kd;
	}

	public double getKDA() {
		return kda;
	}
}
