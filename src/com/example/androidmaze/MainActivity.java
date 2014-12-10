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
	// private EditText mazeR = null;// 显示迷宫
	private TextView mazeR = null;// 显示迷宫
	private EditText row = null;// 输入行号
	private EditText cal = null;// 输入列号
	private EditText maze1 = null;// 手动产生迷宫
	private Button creat = null;// 自动创建迷宫
	private Button shoudong_creat = null;// 手动创建迷宫
	private Button path = null;// 生成路径
	private Button enter = null;// 确定
	private int x;// 行
	private int y;// 列
	private String rowx;
	private String caly;
	private String result = null;
	private int[][] maze /* = new char[26][26] */;
	private Button clear = null;
	private Button shoudong_enterButton = null;// 手动创建确定
	private int i = 0;// 用来控制是否显示空间

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		maze1 = (EditText) findViewById(R.id.maze1);// 输入迷宫的编辑框
		maze1.setBackgroundColor(Color.TRANSPARENT);// 透明
		maze1.setVisibility(View.GONE);// 不可见且不占有空间
		shoudong_creat = (Button) findViewById(R.id.shoudongcreat);// 手动产生迷宫
		// shoudong_enterButton.setVisibility(View.GONE);// 刚开始不显示
		shoudong_enterButton = (Button) findViewById(R.id.shoudong);// 当点击手动创建按钮时，显示该
		shoudong_enterButton.setBackgroundResource(R.drawable.a); // ————————确定创建按钮
		shoudong_enterButton.setVisibility(View.GONE);// 刚开始不显示

		TextView t = (TextView) findViewById(R.id.p);
		t.setText("→");
		mazeR = (TextView) findViewById(R.id.maze);// 显示迷宫
		mazeR.setMovementMethod(ScrollingMovementMethod.getInstance());// 让显示框可以滚动

		row = (EditText) findViewById(R.id.row);// 行号
		cal = (EditText) findViewById(R.id.cal);// 列号

		creat = (Button) findViewById(R.id.creat);// 自动生成迷宫
		creat.setBackgroundResource(R.drawable.a);
		creat.setVisibility(View.GONE);// 首先将自己隐藏

		path = (Button) findViewById(R.id.path);// 生成路径
		path.setBackgroundResource(R.drawable.a);
		path.setVisibility(View.GONE);

		shoudong_creat = (Button) findViewById(R.id.shoudongcreat);// 手动创建迷宫
		shoudong_creat.setBackgroundResource(R.drawable.a);
		shoudong_creat.setVisibility(View.GONE);

		enter = (Button) findViewById(R.id.enter1);
		enter.setBackgroundResource(R.drawable.c);

		enter.setOnClickListener(new OnClickListener() {// 确定,得到行号和列号

			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				mazeR.setText("迷宫产生中。。。。。\n请选择点击创建迷宫！");
				creat.setVisibility(View.VISIBLE);
				shoudong_creat.setVisibility(View.VISIBLE);
				try {
					if (row.getText() != null && cal.getText() != null) {
						rowx = row.getText().toString();
						caly = cal.getText().toString();
						x = Integer.parseInt(rowx);// 获得行号
						y = Integer.parseInt(caly);// 获得列号
						if (y > 20) {
							Toast.makeText(MainActivity.this,
									"由于手机宽度问题，所以请将列数设在20之内！",
									Toast.LENGTH_SHORT).show();
							y = 0;
							cal.setText("");
						}

					} else {
						if (row.getText() == null || cal.getText() == null) {
							Toast.makeText(MainActivity.this, "请输入行号和列号",
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

				mazeR.setText("手动创建的迷宫为:");
				// i = 1;
				// maze = (EditText) findViewById(R.id.maze1);
				maze1.setVisibility(View.VISIBLE);// 可见
				mazeR.setVisibility(View.INVISIBLE);// 不可见
				shoudong_enterButton.setVisibility(View.VISIBLE);// 显示确定按钮
				shoudong_creat.setVisibility(View.GONE);// 将自己隐藏为了显示确定创建按钮
				/*
				 * Intent intent = new Intent(); intent.putExtra("x", x);
				 * intent.putExtra("y", y); intent.setClass(MainActivity.this,
				 * ShouDongCreat.class);//将行号和列号，传过去
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
		shoudong_enterButton.setOnClickListener(new OnClickListener() {// 手动创建后，点击确定创建的监听器

					public void onClick(View v) {
						// TODO Auto-generated method stub
						maze = new int[x][y];

						path.setVisibility(View.VISIBLE);
						try {
							String s = maze1.getText().toString();
							String[] w = s.split("\\.|\n");// 对输入的 0 1 进行分割

							int flag = 0;
							for (int i = 0; i < x; i++) {
								for (int j = 0; j < y; j++) {
									maze[i][j] = Integer.parseInt(w[flag]);// 将输入的0
																			// 1
																			// 存进二位数组里面
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
									if (maze[i][j] == 0) {// 表示可通
										result = mazeR.getText() + "□";
										mazeR.setText(result);
									} else {
										if (maze[i][j] == 1) {// 表示不可通
											result = mazeR.getText() + "■";
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
		clear.setOnClickListener(new OnClickListener() {// 将结果清空，//清除输入的行和列，清除迷宫

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

		// 自动创建
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
				 * mazeR.setText(mazeR.getText()+"迷宫正在生成中。。。。"); try {
				 * m.zhanting(); //thread.sleep(2000); } catch (Exception e) {
				 * // TODO: handle exception e.printStackTrace(); }
				 */

				path.setVisibility(View.VISIBLE);
				mazeR.setText("");
				try {

					maze = new int[x][y];
					for (int i = 0; i < x; i++) {
						for (int j = 0; j < y; j++) {

							maze[i][j] = (int) (Math.random() * 10) % 2;// 要么是1，要么是0
						}
					}
					// 行号和列号那行有值
					maze[0][0] = 0;
					maze[x - 1][y - 1] = 0;
					for (int i = 0; i < x; i++) {
						result = mazeR.getText() + "\n";
						mazeR.setText(result);
						for (int j = 0; j < y; j++) {
							if (maze[i][j] == 0) {// 表示可通
								result = mazeR.getText() + "□";
								mazeR.setText(result);
							} else {
								if (maze[i][j] == 1) {// 表示不可通
									result = mazeR.getText() + "■";
									mazeR.setText(result);
								}

							}
						}

					}
					/*
					 * result = mazeR.getText() + "→迷宫出口\n";
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
					m.path(maze, x, y /* , flag */);// 找路

					/*
					 * System.out.println(maze[0][1] + "***********%%%%%%%*****"
					 * + maze[1][2]);
					 */

					// System.out.println(m.flag);
					if (m.flag == 1) {
						Toast.makeText(MainActivity.this, "此迷宫无解,请重新创建！！",
								Toast.LENGTH_SHORT).show();
						// mazeR.setText(mazeR.getText() + "\n此迷宫无解");
					} else {

						String w = mazeR.getText().toString();// 屏幕上已经有的内容
						String s = new String();
						s = "☆";
						SpannableStringBuilder type = new SpannableStringBuilder(
								"☆");
						type.setSpan(new ForegroundColorSpan(Color.RED), 0, 1,
								Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);// 让字体显示颜色
																	// SPAN_EXCLUSIVE_INCLUSIVE是高亮
						System.out.println(type);
						mazeR.setText("");
						mazeR.setText(mazeR.getText() + "\n" + "迷宫（用☆表示）：如下"
								+ "\n");
						for (int i = 0; i < x; i++) {
							for (int j = 0; j < y; j++) {
								if (maze[i][j] == 0 || maze[i][j] == 2) {
									mazeR.setText(mazeR.getText() + "□");

								} else {
									if (maze[i][j] == 1) {
										mazeR.setText(mazeR.getText() + "■");
									} else {
										if (maze[i][j] == 3) {

											mazeR.setText(mazeR.getText() + "☆");
											// mazeR.setText(mazeR.getText()
											// +Html.fromHtml("<font color=/"#060606/">红色</font>");
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
	public boolean onKeyDown(int keyCode, KeyEvent event) {// 点击返回
		// TODO Auto-generated method stub
		if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
			AlertDialog.Builder ab = new AlertDialog.Builder(MainActivity.this);

			ab.setTitle("退出")
					.setIcon(android.R.drawable.divider_horizontal_dark)
					.setMessage("确定退出：")
					.setPositiveButton("确定",
							new DialogInterface.OnClickListener() {

								public void onClick(DialogInterface arg0,
										int arg1) {
									// TODO Auto-generated method stub
									finish();
								}
							})
					.setNegativeButton("取消",
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

class mgpath {// 找路径
	// private int X;
	int flag = 0;// 控制是否有解
	private int row;// 行
	private int cal;// 列
	private int predecessor;
	// char p[] = new char[41];//队列
	mgpath[] queue = new mgpath[512];// 对象数组，相当于队列
	int head = 0;// 队首
	int tail = 0;// 队尾

	// mgpath mgpath = new mgpath();

	public void visit(int row, int cal, int maze[][]) {

		// this.row = row; this.cal = cal;

		// maze = new int[41][41];
		mgpath visit_point = new mgpath();
		visit_point.row = row;
		visit_point.cal = cal;
		visit_point.predecessor = head - 1;
		maze[row][cal] = 2;// 表示该点已经被访问
		// enqueue(mgpath.row, mgpath.cal, mgpath.head - 1);// 将该节点入队
		enqueue(visit_point);// 将该节点入队

	}

	private void enqueue(mgpath mgpath2) {

		queue[tail] = mgpath2;// 进队列
		tail++;
	}

	public mgpath dequeue() {// 出队
		head++;
		return queue[head - 1];

	}

	public void path(int maze[][], int m, int n /* , int flag */) {
		// X = 1;
		maze[0][0] = 2;// 入口,等于2表示已经分=访问
		mgpath q = new mgpath();// 入口

		// * mgpath p = new mgpath(); p.row = 0; p.cal = 0; p.head = -1;

		q.row = 0;
		q.cal = 0;
		q.predecessor = -1;
		// enqueue(0, 0, -1);// 将第一个点入队
		enqueue(q);// 将第一个点入队
		while (!is_empty()) {// 不为空
			q = dequeue();// 出队列

			if (q.row == m - 1 && q.cal == n - 1) {
				break;
			}
			if ((q.cal + 1 < n) && (maze[q.row][q.cal + 1] == 0)) {// 右边有路
				visit(q.row, q.cal + 1, maze);
			}
			if ((q.row + 1 < m) && (maze[q.row + 1][q.cal] == 0))// 下面有路
				visit(q.row + 1, q.cal, maze);
			if ((q.cal - 1 >= 0) && (maze[q.row][q.cal - 1] == 0))// 左边有路
				visit(q.row, q.cal - 1, maze);
			if ((q.row - 1 >= 0) && (maze[q.row - 1][q.cal] == 0))// 上面有路
				visit(q.row - 1, q.cal, maze);
			if ((q.row - 1 >= 0) && (q.cal - 1 >= 0)
					&& (maze[q.row - 1][q.cal - 1] == 0)) {// 左上方有路
				visit(q.row - 1, q.cal - 1, maze);
			}
			if ((q.row + 1 < m) && (q.cal - 1 >= 0)
					&& (maze[q.row + 1][q.cal - 1] == 0)) {// 右下方有路
				visit(q.row + 1, q.cal - 1, maze);

			}
			if ((q.row - 1 >= 0) && (q.cal + 1 < n)
					&& (maze[q.row - 1][q.cal + 1] == 0)) {// 右上方有路
				visit(q.row - 1, q.cal + 1, maze);
			}
			if ((q.row + 1 < m) && (q.cal + 1 < n)
					&& (maze[q.row + 1][q.cal + 1] == 0)) {// 右下方有路
				visit(q.row + 1, q.cal + 1, maze);

			}
		}
		if (q.row == m - 1 && q.cal == n - 1) {// 到达出口
			maze[q.row][q.cal] = 3;
			while (q.predecessor != -1) {
				q = queue[q.predecessor];
				maze[q.row][q.cal] = 3;
				// System.out.println("%$%$%$%$%$%$%$%$%$%$%$%$%$%$%");
			}

		} else {
			flag = 1;// 表示没路
		}

	}

	public boolean is_empty() {// 判断队列是否为空
		return head == tail;

	}

	public void zhanting() throws InterruptedException {
		Thread.sleep(2000);

	}
}
