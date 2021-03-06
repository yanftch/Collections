package com.iven.widget.yan.imgpicker.util;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Environment;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * 公用工具
 */
public class CommonUtil {
	public final static SimpleDateFormat FULLDATEFORMATNOCONN = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");

	/**
	 * 验证邮箱
	 *
	 * @param email
	 * @return
	 */
	public static boolean checkEmail(String email) {
		boolean flag = false;
		try {
			String check = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
			Pattern regex = Pattern.compile(check);
			Matcher matcher = regex.matcher(email);
			flag = matcher.matches();
		} catch (Exception e) {
			flag = false;
		}
		return flag;
	}

	/**
	 *判断sd卡是否存在
	 * @return
	 */
	public static boolean isSdcardExist() {
		return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
	}


	/**
	 * 验证是否是手机号码
	 *
	 * @param mobiles
	 * @return
	 */
	public static boolean isMobileNumber(String mobiles) {
		return mobiles
				.matches("^((13[0-9])|(14[0-9])|(15[0-9])|(18[0-9]))\\d{8}$");
	}

	/**
	 * 检查是否有网络
	 *
	 * @param mContext
	 * @return
	 */
	public static boolean checkNetwork(Context mContext) {
		ConnectivityManager cwjManager = (ConnectivityManager) mContext
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo info = cwjManager.getActiveNetworkInfo();
		boolean flag = info != null && info.isAvailable() ? true : false;
		if (!flag) {
			CommonUtil.sendToast(mContext, "请检查你的网络");
		}
		return flag;
	}


	/**
	 * toast提示
	 *
	 * @param mContext
	 * @param text
	 */
	public static void sendToast(Context mContext, String text) {
		Toast.makeText(mContext, text, Toast.LENGTH_SHORT).show();
	}



	/**
	 * 检查是否有网络
	 *
	 * @param mContext
	 * @return
	 */
	public static boolean checkNetwork2(Context mContext) {
		ConnectivityManager cwjManager = (ConnectivityManager) mContext
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo info = cwjManager.getActiveNetworkInfo();
		boolean flag = info != null && info.isAvailable() ? true : false;
		return flag;
	}
	/**
	 * 获取外置SD卡路径
	 *
	 * @return
	 */
	public static String getSDCardPath() {
		String cmd = "cat /proc/mounts";
		Runtime run = Runtime.getRuntime();// 返回与当�?Java 应用程序相关的运行时对象
		try {
			Process p = run.exec(cmd);// 启动另一个进程来执行命令
			BufferedInputStream in = new BufferedInputStream(p.getInputStream());
			BufferedReader inBr = new BufferedReader(new InputStreamReader(in));

			String lineStr;
			while ((lineStr = inBr.readLine()) != null) {
				// 获得命令执行后在控制台的输出信
				if (lineStr.contains("sdcard")
						&& lineStr.contains(".android_secure")) {
					String[] strArray = lineStr.split(" ");
					if (strArray != null && strArray.length >= 5) {
						String result = strArray[1].replace("/.android_secure",
								"");
						return result;
					}
				}
			}
			inBr.close();
			in.close();

		} catch (Exception e) {
			return Environment.getExternalStorageDirectory().getPath();
		}
		return Environment.getExternalStorageDirectory().getPath();
	}

	/**
	 * 获取版本号
	 *
	 * @param context
	 * @return
	 */
	public static String getVersionCode(Context context) {
//        String str = "";
//        String packageName;
//        try {
//            packageName = context.getPackageName();
//            str = context.getPackageManager().getPackageInfo(packageName, 0).versionCode + "";
//            return str;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return str;
		String versionName = "";
		try {
			PackageManager pm = context.getPackageManager();
			PackageInfo pi = pm.getPackageInfo(context.getPackageName(), 0);
			versionName = pi.versionName;
			if (versionName == null) {
				return "";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return versionName;
	}

	/**
	 * 判断给定字符串是否空白串。 空白串是指由空格、制表符、回车符、换行符组成的字符串 若输入字符串为null或空字符串，返回true
	 *
	 * @param input
	 * @return boolean
	 */
	public static boolean isEmpty(String input) {
		if (input == null || "".equals(input))
			return true;

		for (int i = 0; i < input.length(); i++) {
			char c = input.charAt(i);
			if (c != ' ' && c != '\t' && c != '\r' && c != '\n') {
				return false;
			}
		}
		return true;
	}

	/**
	 * 判断给定的字符串是否符合微信号规则(6到20个字母，数字，下划线，和减号，必须以字母开头（不区分大小写）)
	 * @param wechat
	 * @return
	 */
	public static boolean isWechat(String wechat){
		return wechat.matches("^[a-zA-Z][a-zA-Z0-9_-]{5,19}$");
	}


	/**
	 * 跳转到目标activity
	 * @param mContext 当前界面
	 * @param mClass 目标activity
	 * @param mBundle 传递参数
     */
	public void startToTargetActivity(Context mContext, Class mClass, Bundle mBundle) {
		Intent mIntent = new Intent(mContext,mClass);
		if(mBundle == null) {
			mBundle = new Bundle();
		}
		mIntent.putExtras(mBundle);
		mContext.startActivity(mIntent);
	}

	/**
	 * 跳转到目标activity
	 * @param mContext 当前界面
	 * @param mClass 目标activity
	 * @param mBundle 传递参数
	 */
	public void startToTargetActivity(Context mContext, Class mClass, Bundle mBundle,int flag) {
		Intent mIntent = new Intent(mContext,mClass);
		if(mBundle == null) {
			mBundle = new Bundle();
		}
		mIntent.putExtras(mBundle);
		mIntent.setFlags(flag);
		mContext.startActivity(mIntent);
	}
}
