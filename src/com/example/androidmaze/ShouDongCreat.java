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

		Intent intent = getIntent();// �õ��¼�
		String stringx = intent.getStringExtra("x");// ���кŴ�����
		x = Integer.parseInt(stringx);// ���ַ����к�ת��Ϊ����
		String stringy = intent.getStringExtra("y");// ���кŴ�����
		y = Integer.parseInt(stringy);// ���ַ����к�ת��Ϊ����

		maze = new int[x][y];// ����x��y�еĶ�λ���飬Ϊ�˴�0��1
		et = (EditText) findViewById(R.id.shoudong);// ��ʾ 0 1 ����
		button = (Button) findViewById(R.id.shoudongcreat);// ȷ��
		button.setOnClickListener(new OnClickListener() {// ���button֮�󣬽�0��1���ڶ�λ��������

			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				String string = et.getText().toString();// ���������ַ���
				String[] s = string.split(",");// �ԣ�Ϊ�ָ�����ַ�����������

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
