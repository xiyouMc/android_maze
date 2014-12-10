package com.example.androidmaze;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class ShouDongCreat extends Activity {
	private int x;
	private int y;
	private EditText et = null;
	private int[][] maze;
	private Button button = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.maze);

		Intent intent = getIntent();// 得到事件
		String stringx = intent.getStringExtra("x");// 将行号传过来
		x = Integer.parseInt(stringx);// 将字符串行号转换为数字
		String stringy = intent.getStringExtra("y");// 将列号传过来
		y = Integer.parseInt(stringy);// 将字符串列号转换为数字

		maze = new int[x][y];// 定义x行y列的二位数组，为了存0和1
		et = (EditText) findViewById(R.id.shoudong);// 显示 0 1 数组
		button = (Button) findViewById(R.id.shoudongcreat);// 确定
		button.setOnClickListener(new OnClickListener() {// 点击button之后，将0和1存在二位数组里面

			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				String string = et.getText().toString();// 获得输入的字符串
				String[] s = string.split(",");// 以，为分割点存进字符串数组里面

				int flag = 0;
				for (int i = 0; i < x; i++) {
					for (int j = 0; j < y; j++) {
						maze[i][j] = Integer.parseInt(s[flag]);
						flag++;
					}
				}
			}
		});

	}

}
