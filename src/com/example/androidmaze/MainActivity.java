package com.example.androidmaze;

import java.security.PublicKey;

import android.os.Bundle;
import android.R.integer;
import android.R.string;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Path;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewDebug.FlagToString;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v4.app.NavUtils;
import android.text.Html;
import android.text.InputType;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.method.ScrollingMovementMethod;
import android.text.style.ForegroundColorSpan;

@SuppressWarnings("unused")
public class MainActivity extends Activity {
	// private EditText mazeR = null;// ��ʾ�Թ�
	private TextView mazeR = null;// ��ʾ�Թ�
	private EditText row = null;// �����к�
	private EditText cal = null;// �����к�
	private EditText maze1 = null;// �ֶ������Թ�
	private Button creat = null;// �Զ������Թ�
	private Button shoudong_creat = null;// �ֶ������Թ�
	private Button path = null;// ����·��
	private Button enter = null;// ȷ��
	private int x;// ��
	private int y;// ��
	private String rowx;
	private String caly;
	private String result = null;
	private int[][] maze /* = new char[26][26] */;
	private Button clear = null;
	private Button shoudong_enterButton = null;// �ֶ�����ȷ��
	private int i = 0;// ���������Ƿ���ʾ�ռ�

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		maze1 = (EditText) findViewById(R.id.maze1);// �����Թ��ı༭��
		maze1.setBackgroundColor(Color.TRANSPARENT);// ͸��
		maze1.setVisibility(View.GONE);// ���ɼ��Ҳ�ռ�пռ�
		shoudong_creat = (Button) findViewById(R.id.shoudongcreat);// �ֶ������Թ�
		// shoudong_enterButton.setVisibility(View.GONE);// �տ�ʼ����ʾ
		shoudong_enterButton = (Button) findViewById(R.id.shoudong);// ������ֶ�������ťʱ����ʾ��
		shoudong_enterButton.setBackgroundResource(R.drawable.a); // ����������������ȷ��������ť
		shoudong_enterButton.setVisibility(View.GONE);// �տ�ʼ����ʾ

		TextView t = (TextView) findViewById(R.id.p);
		t.setText("��");
		mazeR = (TextView) findViewById(R.id.maze);// ��ʾ�Թ�
		mazeR.setMovementMethod(ScrollingMovementMethod.getInstance());// ����ʾ����Թ���

		row = (EditText) findViewById(R.id.row);// �к�
		cal = (EditText) findViewById(R.id.cal);// �к�

		creat = (Button) findViewById(R.id.creat);// �Զ������Թ�
		creat.setBackgroundResource(R.drawable.a);
		creat.setVisibility(View.GONE);// ���Ƚ��Լ�����

		path = (Button) findViewById(R.id.path);// ����·��
		path.setBackgroundResource(R.drawable.a);
		path.setVisibility(View.GONE);

		shoudong_creat = (Button) findViewById(R.id.shoudongcreat);// �ֶ������Թ�
		shoudong_creat.setBackgroundResource(R.drawable.a);
		shoudong_creat.setVisibility(View.GONE);

		enter = (Button) findViewById(R.id.enter1);
		enter.setBackgroundResource(R.drawable.c);

		enter.setOnClickListener(new OnClickListener() {// ȷ��,�õ��кź��к�

			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				mazeR.setText("�Թ������С���������\n��ѡ���������Թ���");
				creat.setVisibility(View.VISIBLE);
				shoudong_creat.setVisibility(View.VISIBLE);
				try {
					if (row.getText() != null && cal.getText() != null) {
						rowx = row.getText().toString();
						caly = cal.getText().toString();
						x = Integer.parseInt(rowx);// ����к�
						y = Integer.parseInt(caly);// ����к�
						if (y > 20) {
							Toast.makeText(MainActivity.this,
									"�����ֻ�������⣬�����뽫��������20֮�ڣ�",
									Toast.LENGTH_SHORT).show();
							y = 0;
							cal.setText("");
						}

					} else {
						if (row.getText() == null || cal.getText() == null) {
							Toast.makeText(MainActivity.this, "�������кź��к�",
									Toast.LENGTH_SHORT).show();
						}

					}
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
		});
		enter.setOnTouchListener(new OnTouchListener() {

			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				if (event.getAction() == MotionEvent.ACTION_DOWN) {
					enter.setBackgroundResource(R.drawable.b);
				} else {
					if (event.getAction() == MotionEvent.ACTION_UP) {
						enter.setBackgroundResource(R.drawable.c);
					}
				}
				return false;
			}
		});

		shoudong_creat.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub

				mazeR.setText("�ֶ��������Թ�Ϊ:");
				// i = 1;
				// maze = (EditText) findViewById(R.id.maze1);
				maze1.setVisibility(View.VISIBLE);// �ɼ�
				mazeR.setVisibility(View.INVISIBLE);// ���ɼ�
				shoudong_enterButton.setVisibility(View.VISIBLE);// ��ʾȷ����ť
				shoudong_creat.setVisibility(View.GONE);// ���Լ�����Ϊ����ʾȷ��������ť
				/*
				 * Intent intent = new Intent(); intent.putExtra("x", x);
				 * intent.putExtra("y", y); intent.setClass(MainActivity.this,
				 * ShouDongCreat.class);//���кź��кţ�����ȥ
				 * MainActivity.this.startActivity(intent);
				 */

			}
		});
		shoudong_creat.setOnTouchListener(new OnTouchListener() {

			public boolean onTouch(View arg0, MotionEvent arg1) {
				// TODO Auto-generated method stub
				if (arg1.getAction() == MotionEvent.ACTION_DOWN) {
					shoudong_creat.setBackgroundResource(R.drawable.b);
				} else {
					if (arg1.getAction() == MotionEvent.ACTION_UP) {
						shoudong_creat.setBackgroundResource(R.drawable.a);
					}
				}
				return false;
			}
		});
		shoudong_enterButton.setOnClickListener(new OnClickListener() {// �ֶ������󣬵��ȷ�������ļ�����

					public void onClick(View v) {
						// TODO Auto-generated method stub
						maze = new int[x][y];

						path.setVisibility(View.VISIBLE);
						try {
							String s = maze1.getText().toString();
							String[] w = s.split("\\.|\n");// ������� 0 1 ���зָ�

							int flag = 0;
							for (int i = 0; i < x; i++) {
								for (int j = 0; j < y; j++) {
									maze[i][j] = Integer.parseInt(w[flag]);// �������0
																			// 1
																			// �����λ��������
									flag++;
								}
							}

							maze1.setVisibility(View.GONE);
							mazeR.setVisibility(View.VISIBLE);
							shoudong_enterButton.setVisibility(View.GONE);
							shoudong_creat.setVisibility(View.VISIBLE);
							maze[0][0] = 0;
							maze[x - 1][y - 1] = 0;
							for (int i = 0; i < x; i++) {
								result = mazeR.getText() + "\n";
								mazeR.setText(result);
								for (int j = 0; j < y; j++) {
									if (maze[i][j] == 0) {// ��ʾ��ͨ
										result = mazeR.getText() + "��";
										mazeR.setText(result);
									} else {
										if (maze[i][j] == 1) {// ��ʾ����ͨ
											result = mazeR.getText() + "��";
											mazeR.setText(result);
										}

									}
								}

							}
						} catch (Exception e) {
							// TODO: handle exception
							e.printStackTrace();
						}

					}
				});
		shoudong_enterButton.setOnTouchListener(new OnTouchListener() {

			public boolean onTouch(View arg0, MotionEvent arg1) {
				// TODO Auto-generated method stub
				if (arg1.getAction() == MotionEvent.ACTION_DOWN) {
					shoudong_enterButton.setBackgroundResource(R.drawable.b);
				} else {
					if (arg1.getAction() == MotionEvent.ACTION_UP) {
						shoudong_enterButton
								.setBackgroundResource(R.drawable.a);
					}
				}
				return false;
			}
		});

		path.setOnTouchListener(new OnTouchListener() {

			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				if (event.getAction() == MotionEvent.ACTION_DOWN) {
					path.setBackgroundResource(R.drawable.b);
				} else {
					if (event.getAction() == MotionEvent.ACTION_UP) {
						path.setBackgroundResource(R.drawable.a);
					}
				}
				return false;
			}
		});

		clear = (Button) findViewById(R.id.clear1);
		clear.setBackgroundResource(R.drawable.c);
		clear.setOnTouchListener(new OnTouchListener() {

			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				if (event.getAction() == MotionEvent.ACTION_DOWN) {
					clear.setBackgroundResource(R.drawable.b);
				} else {
					if (event.getAction() == MotionEvent.ACTION_UP) {
						clear.setBackgroundResource(R.drawable.c);
					}
				}
				return false;
			}
		});
		clear.setOnClickListener(new OnClickListener() {// �������գ�//���������к��У�����Թ�

			public void onClick(View v) {
				// TODO Auto-generated method stub
				mazeR.setText("");
				maze1.setText("");
				row.setText("");
				cal.setText("");
				x = 0;
				y = 0;
			}
		});

		// �Զ�����
		creat.setOnTouchListener(new OnTouchListener() {

			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				if (event.getAction() == MotionEvent.ACTION_DOWN) {
					creat.setBackgroundResource(R.drawable.b);
				} else {
					if (event.getAction() == MotionEvent.ACTION_UP) {
						creat.setBackgroundResource(R.drawable.a);
					}
				}
				return false;
			}
		});
		creat.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				// Thread thread = new Thread();
				/*
				 * mgpath m = new mgpath(); mazeR.setText("");
				 * mazeR.setText(mazeR.getText()+"�Թ����������С�������"); try {
				 * m.zhanting(); //thread.sleep(2000); } catch (Exception e) {
				 * // TODO: handle exception e.printStackTrace(); }
				 */

				path.setVisibility(View.VISIBLE);
				mazeR.setText("");
				try {

					maze = new int[x][y];
					for (int i = 0; i < x; i++) {
						for (int j = 0; j < y; j++) {

							maze[i][j] = (int) (Math.random() * 10) % 2;// Ҫô��1��Ҫô��0
						}
					}
					// �кź��к�������ֵ
					maze[0][0] = 0;
					maze[x - 1][y - 1] = 0;
					for (int i = 0; i < x; i++) {
						result = mazeR.getText() + "\n";
						mazeR.setText(result);
						for (int j = 0; j < y; j++) {
							if (maze[i][j] == 0) {// ��ʾ��ͨ
								result = mazeR.getText() + "��";
								mazeR.setText(result);
							} else {
								if (maze[i][j] == 1) {// ��ʾ����ͨ
									result = mazeR.getText() + "��";
									mazeR.setText(result);
								}

							}
						}

					}
					/*
					 * result = mazeR.getText() + "���Թ�����\n";
					 * mazeR.setText(result);
					 */

				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}

			}
		});

		path.setOnClickListener(new OnClickListener() {
			// int flag;

			public void onClick(View arg0) {
				// TODO Auto-generated method stub

				try {
					mgpath m = new mgpath();
					m.path(maze, x, y /* , flag */);// ��·

					/*
					 * System.out.println(maze[0][1] + "***********%%%%%%%*****"
					 * + maze[1][2]);
					 */

					// System.out.println(m.flag);
					if (m.flag == 1) {
						Toast.makeText(MainActivity.this, "���Թ��޽�,�����´�������",
								Toast.LENGTH_SHORT).show();
						// mazeR.setText(mazeR.getText() + "\n���Թ��޽�");
					} else {

						String w = mazeR.getText().toString();// ��Ļ���Ѿ��е�����
						String s = new String();
						s = "��";
						SpannableStringBuilder type = new SpannableStringBuilder(
								"��");
						type.setSpan(new ForegroundColorSpan(Color.RED), 0, 1,
								Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);// ��������ʾ��ɫ
																	// SPAN_EXCLUSIVE_INCLUSIVE�Ǹ���
						System.out.println(type);
						mazeR.setText("");
						mazeR.setText(mazeR.getText() + "\n" + "�Թ����á��ʾ��������"
								+ "\n");
						for (int i = 0; i < x; i++) {
							for (int j = 0; j < y; j++) {
								if (maze[i][j] == 0 || maze[i][j] == 2) {
									mazeR.setText(mazeR.getText() + "��");

								} else {
									if (maze[i][j] == 1) {
										mazeR.setText(mazeR.getText() + "��");
									} else {
										if (maze[i][j] == 3) {

											mazeR.setText(mazeR.getText() + "��");
											// mazeR.setText(mazeR.getText()
											// +Html.fromHtml("<font color=/"#060606/">��ɫ</font>");
											// mazeR.setText( mazeR.getText() +
											// type);
											// mazeR.setText( w + type);
										}
									}
								}
							}
							mazeR.setText(mazeR.getText() + "\n");
						}
					}
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}

			}
		});

	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {// �������
		// TODO Auto-generated method stub
		if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
			AlertDialog.Builder ab = new AlertDialog.Builder(MainActivity.this);

			ab.setTitle("�˳�")
					.setIcon(android.R.drawable.divider_horizontal_dark)
					.setMessage("ȷ���˳���")
					.setPositiveButton("ȷ��",
							new DialogInterface.OnClickListener() {

								public void onClick(DialogInterface arg0,
										int arg1) {
									// TODO Auto-generated method stub
									finish();
								}
							})
					.setNegativeButton("ȡ��",
							new DialogInterface.OnClickListener() {

								public void onClick(DialogInterface dialog,
										int which) {
									// TODO Auto-generated method stub
									dialog.cancel();
								}
							}).create().show();
		}

		return true;
	}


}

class mgpath {// ��·��
	// private int X;
	int flag = 0;// �����Ƿ��н�
	private int row;// ��
	private int cal;// ��
	private int predecessor;
	// char p[] = new char[41];//����
	mgpath[] queue = new mgpath[512];// �������飬�൱�ڶ���
	int head = 0;// ����
	int tail = 0;// ��β

	// mgpath mgpath = new mgpath();

	public void visit(int row, int cal, int maze[][]) {

		// this.row = row; this.cal = cal;

		// maze = new int[41][41];
		mgpath visit_point = new mgpath();
		visit_point.row = row;
		visit_point.cal = cal;
		visit_point.predecessor = head - 1;
		maze[row][cal] = 2;// ��ʾ�õ��Ѿ�������
		// enqueue(mgpath.row, mgpath.cal, mgpath.head - 1);// ���ýڵ����
		enqueue(visit_point);// ���ýڵ����

	}

	private void enqueue(mgpath mgpath2) {

		queue[tail] = mgpath2;// ������
		tail++;
	}

	public mgpath dequeue() {// ����
		head++;
		return queue[head - 1];

	}

	public void path(int maze[][], int m, int n /* , int flag */) {
		// X = 1;
		maze[0][0] = 2;// ���,����2��ʾ�Ѿ���=����
		mgpath q = new mgpath();// ���

		// * mgpath p = new mgpath(); p.row = 0; p.cal = 0; p.head = -1;

		q.row = 0;
		q.cal = 0;
		q.predecessor = -1;
		// enqueue(0, 0, -1);// ����һ�������
		enqueue(q);// ����һ�������
		while (!is_empty()) {// ��Ϊ��
			q = dequeue();// ������

			if (q.row == m - 1 && q.cal == n - 1) {
				break;
			}
			if ((q.cal + 1 < n) && (maze[q.row][q.cal + 1] == 0)) {// �ұ���·
				visit(q.row, q.cal + 1, maze);
			}
			if ((q.row + 1 < m) && (maze[q.row + 1][q.cal] == 0))// ������·
				visit(q.row + 1, q.cal, maze);
			if ((q.cal - 1 >= 0) && (maze[q.row][q.cal - 1] == 0))// �����·
				visit(q.row, q.cal - 1, maze);
			if ((q.row - 1 >= 0) && (maze[q.row - 1][q.cal] == 0))// ������·
				visit(q.row - 1, q.cal, maze);
			if ((q.row - 1 >= 0) && (q.cal - 1 >= 0)
					&& (maze[q.row - 1][q.cal - 1] == 0)) {// ���Ϸ���·
				visit(q.row - 1, q.cal - 1, maze);
			}
			if ((q.row + 1 < m) && (q.cal - 1 >= 0)
					&& (maze[q.row + 1][q.cal - 1] == 0)) {// ���·���·
				visit(q.row + 1, q.cal - 1, maze);

			}
			if ((q.row - 1 >= 0) && (q.cal + 1 < n)
					&& (maze[q.row - 1][q.cal + 1] == 0)) {// ���Ϸ���·
				visit(q.row - 1, q.cal + 1, maze);
			}
			if ((q.row + 1 < m) && (q.cal + 1 < n)
					&& (maze[q.row + 1][q.cal + 1] == 0)) {// ���·���·
				visit(q.row + 1, q.cal + 1, maze);

			}
		}
		if (q.row == m - 1 && q.cal == n - 1) {// �������
			maze[q.row][q.cal] = 3;
			while (q.predecessor != -1) {
				q = queue[q.predecessor];
				maze[q.row][q.cal] = 3;
				// System.out.println("%$%$%$%$%$%$%$%$%$%$%$%$%$%$%");
			}

		} else {
			flag = 1;// ��ʾû·
		}

	}

	public boolean is_empty() {// �ж϶����Ƿ�Ϊ��
		return head == tail;

	}

	public void zhanting() throws InterruptedException {
		Thread.sleep(2000);

	}
}
