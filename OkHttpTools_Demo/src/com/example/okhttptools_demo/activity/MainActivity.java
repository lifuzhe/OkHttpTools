package com.example.okhttptools_demo.activity;

import java.io.File;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;
import cn.finalteam.toolsfinal.JsonFormatUtils;

import com.alibaba.fastjson.JSONObject;
import com.example.okhttptools_demo.R;
import com.example.okhttptools_demo.bean.LoginResponse;
import com.example.okhttptools_demo.bean.UploadResponse;
import com.example.okhttptools_demo.http.HttpApi;
import com.hs.okhttptools.BaseHttpRequestCallback;
import com.hs.okhttptools.FileDownloadCallback;
import com.hs.okhttptools.HttpRequest;
import com.hs.okhttptools.JsonHttpRequestCallback;
import com.hs.okhttptools.RequestParams;
import com.hs.okhttptools.StringHttpRequestCallback;

public class MainActivity extends ActionBarActivity {

	private String fileUrlPath = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	/**
	 * TODO 响应数据解析成bean
	 * 
	 * @param view
	 */
	public void parseBean(View view) {
		RequestParams params = new RequestParams();
		params.addFormDataPart("userName", "test8888");
		params.addFormDataPart("passWord", "test54188");
		params.addFormDataPart("token", "edopljklkmi");

		HttpRequest.post(HttpApi.PARSEBEANURL, params,
				new BaseHttpRequestCallback<LoginResponse>() {
					@Override
					public void onStart() { // 请求开始(此处显示进度圈圈, 具体业务自行处理)
					}

					@Override
					protected void onSuccess(LoginResponse loginResponse) { // 请求success
						fileUrlPath = loginResponse.fileUrlPath;

						Toast.makeText(getApplicationContext(),
								loginResponse.toString(), Toast.LENGTH_SHORT)
								.show();
					}

					@Override
					public void onFailure(int errorCode, String msg) { // 请求fail
						Toast.makeText(getApplicationContext(), msg,
								Toast.LENGTH_SHORT).show();
					}

					@Override
					public void onFinish() {// 请求结束(此处关闭进度圈圈, 具体业务自行处理)
					}
				});

	}

	/**
	 * TODO 响应数据解析成String
	 * 
	 * @param view
	 */
	public void parseString(View view) {
		RequestParams params = new RequestParams();
		params.addFormDataPart("userName", "test8888");
		params.addFormDataPart("passWord", "test54188");
		params.addFormDataPart("token", "edopljklkmi");

		HttpRequest.post(HttpApi.PARSEBEANURL, params,
				new StringHttpRequestCallback() {
					@Override
					public void onStart() {
						super.onStart();
					}

					@Override
					protected void onSuccess(String response) {
						super.onSuccess(response);
						Toast.makeText(getApplicationContext(), response,
								Toast.LENGTH_SHORT).show();
					}

					@Override
					public void onFailure(int errorCode, String msg) {
						super.onFailure(errorCode, msg);
						Toast.makeText(getApplicationContext(), msg,
								Toast.LENGTH_SHORT).show();
					}

					@Override
					public void onFinish() {
						super.onFinish();
					}
				});
	}

	/**
	 * TODO 响应数据解析jsonObject
	 * 
	 * @param view
	 */
	public void parseJsonObject(View view) {
		RequestParams params = new RequestParams();
		params.addFormDataPart("userName", "test8888");
		params.addFormDataPart("passWord", "test54188");
		params.addFormDataPart("token", "edopljklkmi");

		HttpRequest.post(HttpApi.PARSEBEANURL, params,
				new JsonHttpRequestCallback() {
					@Override
					public void onStart() {
						super.onStart();
					}

					@Override
					protected void onSuccess(JSONObject response) {
						super.onSuccess(response);
						Toast.makeText(
								getApplicationContext(),
								JsonFormatUtils.formatJson(response
										.toJSONString()), Toast.LENGTH_SHORT)
								.show();
					}

					@Override
					public void onFailure(int errorCode, String msg) {
						super.onFailure(errorCode, msg);
						Toast.makeText(getApplicationContext(), msg,
								Toast.LENGTH_SHORT).show();
					}

					@Override
					public void onFinish() {
						super.onFinish();
					}
				});
	}

	/**
	 * TODO 文件下载
	 * 
	 * @param view
	 */
	@SuppressLint("SdCardPath")
	public void fileDownload(View view) {
		if (!Environment.getExternalStorageState().equals(
				android.os.Environment.MEDIA_MOUNTED)) {
			Toast.makeText(this, "sd卡不存在, 请先插入sd卡!", Toast.LENGTH_LONG).show();
			return;
		}

		if (fileUrlPath == null || TextUtils.equals(fileUrlPath, "")) {
			Toast.makeText(this, "下载路径为空, 先点击下解析bean的按钮!", Toast.LENGTH_LONG)
					.show();
			return;
		}

		HttpRequest.download(fileUrlPath, new File(
				"/sdcard/OkHttpTools_Demo.apk"), new FileDownloadCallback() {
			@Override
			public void onStart() {
				super.onStart();
			}

			@Override
			public void onProgress(int progress, long networkSpeed) {
				super.onProgress(progress, networkSpeed); // 下载的进度,下载的网速
			}

			@Override
			public void onFailure() {
				super.onFailure();
				Toast.makeText(getApplicationContext(), "下载失败",
						Toast.LENGTH_LONG).show();
			}

			@Override
			public void onDone() {
				super.onDone();
				Toast.makeText(getApplicationContext(), "下载成功",
						Toast.LENGTH_LONG).show();
			}
		});
	}

	/**
	 * TODO 文件上传
	 * 
	 * @param view
	 */
	public void fileUpload(View view) {
		// 打开手机相册
		Intent intent = new Intent(Intent.ACTION_PICK,
				android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
		startActivityForResult(intent, 10369);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		// 获取图片路径
		if (requestCode == 10369 && resultCode == Activity.RESULT_OK
				&& data != null) {
			Uri selectedImage = data.getData();
			String[] filePathColumns = { MediaStore.Images.Media.DATA };
			Cursor c = getContentResolver().query(selectedImage,
					filePathColumns, null, null, null);
			c.moveToFirst();
			int columnIndex = c.getColumnIndex(filePathColumns[0]);
			String imagePath = c.getString(columnIndex);
			c.close();
			
			if (imagePath != null) {
				if (!TextUtils.equals("", imagePath)) {
					// TODO 上传至服务器
					RequestParams params = new RequestParams();
					params.addFormDataPart("file", new File(imagePath));

					HttpRequest.post(HttpApi.UPLOADURL, params,
							new BaseHttpRequestCallback<UploadResponse>() {
								@Override
								public void onSuccess(UploadResponse uploadResponse) {
									super.onSuccess(uploadResponse);
									Toast.makeText(getBaseContext(), uploadResponse.message,
											Toast.LENGTH_SHORT).show();
								}

								@Override
								public void onFailure(int errorCode, String msg) {
									super.onFailure(errorCode, msg);
									Toast.makeText(getBaseContext(), "上传失败",
											Toast.LENGTH_SHORT).show();
								}

								@Override
								public void onProgress(int progress, long networkSpeed,
										boolean done) {
								}

								@Override
								public void onStart() {
									super.onStart();
								}
							});
				}
			}
		}
	}

}
