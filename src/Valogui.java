import java.awt.Button;
import java.awt.Checkbox;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;

public class Valogui implements ActionListener, WindowListener {

	private static final int FRAME_WIDTH = 480;
	private static final int FRAME_HEIGHT = 500;

	private Panel rightPanel;
	private Panel leftPanel;
	private Panel bottomPanel;
	private Label selectStage;
	private ArrayList<Checkbox> stages = new ArrayList<>();
	private Frame frame;
	private ArrayList<TextField> team1s;
	private ArrayList<TextField> team2s;
	private ArrayList<String> member1 = new ArrayList<>();
	private ArrayList<String> member2 = new ArrayList<>();
	private ValorantAllMember valo;
	private Label team1kd;
	private Label team2kd;
	private Label kdgap;

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO 自動生成されたメソッド・スタブ
		System.out.println("押した");
		valo = new ValorantAllMember();
		valo.groupingRepeat(valo, 0.5);
		valo.showTeam();
		//ラベルに表示 Math.floor(valo.getKDGap(valo) / 5 * 100) / 100
		team1kd.setText("team1のK/D:" + Math.floor(valo.getSumKD(valo.getTeam1()) / 5 * 100) / 100);
		team2kd.setText("team2のK/D:" + Math.floor(valo.getSumKD(valo.getTeam2()) / 5 * 100) / 100);
		kdgap.setText("2チームのK/Dの差:" + Math.floor(valo.getKDGap(valo) / 5 * 100) / 100);
		member1.clear();
		for (ValorantAve player : valo.getTeam1()) {
			member1.add(player.getName());
		}
		/*
		member1.add("一蘭");
		member1.add("来来亭");
		member1.add("天下一品");
		member1.add("神座");
		member1.add("横綱ﾗｰﾒﾝ");
		*/
		for (int i = 0; i < 5; i++) {
			team1s.get(i).setText(member1.get(i));
		}

		member2.clear();
		/*
		member2.add("キラメキ");
		member2.add("きりん寺");
		member2.add("あっぱれらーめん");
		member2.add("希望新風");
		member2.add("ﾗｰﾒﾝまこと屋");
		*/
		for (ValorantAve player : valo.getTeam2()) {
			member2.add(player.getName());
		}
		for (int i = 0; i < 5; i++) {
			team2s.get(i).setText(member2.get(i));
		}

		//ステージ処理
		ArrayList<String> availStage = new ArrayList<>();
		for (Checkbox stage : stages) {
			if (!stage.getState())
				availStage.add(stage.getLabel());
		}
		Random random = new Random();

		if (availStage.size() != 0) {
			int rand = random.nextInt(availStage.size());
			String stage = availStage.get(rand);
			selectStage.setText(availStage.get(rand));
		} else {
			selectStage.setText("全部BANされています");
		}
	}

	public void createView() {
		frame = new Frame("ValoSelector");
		frame.setResizable(false);
		frame.addWindowListener(this);
		Image icon = null;
		try {
			icon = ImageIO.read(new File("Pachinkobitobig.jpg"));
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		frame.setIconImage(icon);
		Panel allPanel = new Panel(new GridLayout(2, 1));

		Panel topPanel = new Panel(new GridLayout(2, 2));
		topPanel.setBackground(Color.white);
		leftPanel = new Panel(new GridLayout(6, 1));
		leftPanel.setBackground(Color.white);
		rightPanel = new Panel(new GridLayout(6, 1));
		rightPanel.setBackground(Color.white);
		bottomPanel = new Panel(new GridLayout(1, 2));
		bottomPanel.setBackground(Color.yellow);

		Panel leftButtonPanel = new Panel(new GridBagLayout());
		leftButtonPanel.setBackground(Color.gray);
		GridBagConstraints gbc = new GridBagConstraints();
		Button run = new Button("設定生成");
		run.addActionListener(this);
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 1;
		gbc.gridheight = 2;
		gbc.weightx = 1.0d;
		gbc.anchor = GridBagConstraints.WEST;
		leftButtonPanel.add(run, gbc);
		Button onlymen = new Button("メンバー再配置");
		gbc.anchor = GridBagConstraints.CENTER;
		leftButtonPanel.add(onlymen, gbc);

		Panel toprightPanel = new Panel(new GridLayout(7, 1));

		stages.add(new Checkbox("ブリーズ", false));
		stages.add(new Checkbox("アイスボックス", false));
		stages.add(new Checkbox("バインド", false));
		stages.add(new Checkbox("ヘイヴン", false));
		stages.add(new Checkbox("スプリット", false));
		stages.add(new Checkbox("アセント", false));
		Label stageEx = new Label("Banステージ選択");
		stageEx.setBackground(Color.gray);
		stageEx.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 12));
		toprightPanel.add(stageEx);
		for (int i = 0; i < 6; i++) {
			toprightPanel.add(stages.get(i));
		}

		Panel topleftbottomPanel = new Panel(new GridLayout(4, 1));
		Label ex = new Label("詳細情報");
		ex.setBackground(Color.gray);
		ex.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 12));
		team1kd = new Label("team1のK/D");
		team1kd.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 12));
		team2kd = new Label("team2のK/D");
		team2kd.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 12));
		kdgap = new Label("2チームのK/Dの差");
		kdgap.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 12));
		team2kd.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 12));
		topleftbottomPanel.add(ex);
		topleftbottomPanel.add(team1kd);
		topleftbottomPanel.add(team2kd);
		topleftbottomPanel.add(kdgap);

		Panel toprightbottomPanel = new Panel(new GridLayout(4, 1));
		ex = new Label("選択されたMap");
		ex.setBackground(Color.gray);
		ex.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 12));
		selectStage = new Label("ここに表示");
		selectStage.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 12));
		toprightbottomPanel.add(ex);
		toprightbottomPanel.add(selectStage);

		topPanel.add(leftButtonPanel);
		topPanel.add(toprightPanel);
		topPanel.add(topleftbottomPanel);
		topPanel.add(toprightbottomPanel);

		Label labelteam1 = new Label("<Team1メンバー>");
		labelteam1.setBackground(Color.gray);
		labelteam1.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));

		for (int i = 0; i < 5; i++) {
			member1.add("Member" + (i + 1));
		}
		team1s = returnTeam(member1);

		Label labelteam2 = new Label("<Team2メンバー>");
		labelteam2.setBackground(Color.gray);
		labelteam2.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));

		for (int i = 0; i < 5; i++) {
			member2.add("Member" + (i + 6));
		}

		team2s = returnTeam(member2);
		leftPanel.add(labelteam1);
		//leftPanel.add(team1);
		for (TextField team : team1s) {
			leftPanel.add(team);
		}
		rightPanel.add(labelteam2);
		//rightPanel.add(team2);
		for (TextField team : team2s) {
			rightPanel.add(team);
		}
		allPanel.add(topPanel);
		bottomPanel.add(leftPanel);
		bottomPanel.add(rightPanel);
		allPanel.add(bottomPanel);
		frame.add(allPanel);
		frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		frame.setVisible(true);

	}

	public ArrayList<TextField> returnTeam(ArrayList<String> member) {
		ArrayList<TextField> team = new ArrayList<>();
		for (String name : member) {
			team.add(new TextField(name));
			team.get(team.size() - 1).setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
			team.get(team.size() - 1).setEditable(false);
		}
		return team;
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO 自動生成されたメソッド・スタブ
		System.exit(0);

	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO 自動生成されたメソッド・スタブ

	}

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		Valogui valo = new Valogui();
		valo.createView();
	}

}
