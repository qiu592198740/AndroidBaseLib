package com.owl.baselib.utils;

import java.io.File;
import java.io.IOException;

import android.content.Context;
import android.os.Environment;

public class FileUtils {

	public static boolean isDirExist(String dirPath) {
		File dir = new File(dirPath);
		return dir.exists() && dir.isDirectory();
	}

	/**
	 * 检测SD卡是否存在
	 */
	public static boolean checkSDcard() {
		return Environment.MEDIA_MOUNTED.equals(Environment
				.getExternalStorageState());
	}

	/**
	 * 获取文件保存点
	 */
	public static File getSaveFile(Context context, String fileName) {

		File file = null;
		if (checkSDcard()) {
			file = new File(getExternalSavePath(context, fileName));
		}
		else {
			file = new File(getInternalSavePath(context, fileName));
		}

		return file;
	}

	/**
	 * 获取文件保存路径
	 */
	public static String getInternalSavePath(Context context, String fileName) {
		File file = new File(context.getFilesDir().getAbsolutePath());
		file.mkdirs();
		return file.getAbsolutePath() + "/" + fileName;
	}

	/**
	 * 获取文件保存路径
	 */
	public static String getExternalSavePath(Context context, String fileName) {
		
		File externalFile = context.getExternalFilesDir(null);
		if (externalFile == null) {
			externalFile = context.getExternalCacheDir();
		}
		File file = new File(externalFile.getAbsolutePath());
		file.mkdirs();
		return file.getAbsolutePath() + "/" + fileName;
	}

	/**
	 * 判断SD卡上的文件是否存在
	 * 
	 * @param fileName
	 * @return
	 */
	public static boolean isFileExist(String filePath) {
		File file = new File(filePath);
		return file.exists();
	}

	public static void createDir(String... dirPath) {
		File dir = null;
		for (int i = 0; i < dirPath.length; i++) {
			dir = new File(dirPath[i]);
			if (!dir.exists() && !dir.isDirectory()) {
				dir.mkdirs();
			}
		}
	}

	/**
	 * 在SD卡上创建文件
	 * 
	 * @param fileName
	 * @return
	 * @throws IOException
	 */
	public static File createSDFile(String path) throws IOException {
		File file = new File(path);
		if (!file.exists())
			file.createNewFile();

		return file;
	}

	/**
	 * 在SD卡上创建目录
	 * 
	 * @param dirName
	 * @return
	 */
	public static File createSDDir(String dirName) {
		File file = new File(dirName);
		if (!file.exists())
			file.mkdir();
		return file;
	}

	public static void createFileDir(String filePath) {
		File file = new File(filePath);
		if (!file.exists()) {
			File parentFile = file.getParentFile();
			if (!parentFile.exists()) {
				parentFile.mkdirs();
			}
			parentFile = null;
		}
		file = null;
	}

	public static boolean deleteFile(String fileName) {
		try {
			if (fileName == null) {
				return false;
			}
			File f = new File(fileName);

			if (f == null || !f.exists()) {
				return false;
			}

			if (f.isDirectory()) {
				return false;
			}
			return f.delete();
		}
		catch (Exception e) {
			// Log.d(FILE_TAG, e.getMessage());
			return false;
		}
	}

	public static boolean deleteFileOfDir(String dirName, boolean isRecurse) {
		boolean blret = false;
		try {
			File f = new File(dirName);
			if (f == null || !f.exists()) {
				// Log.d(FILE_TAG, "file" + dirName + "not isExist");
				return false;
			}

			if (f.isFile()) {
				blret = f.delete();
				return blret;
			}
			else {
				File[] flst = f.listFiles();
				if (flst == null || flst.length <= 0) {
					return true;
				}

				int filenumber = flst.length;
				File[] fchilda = f.listFiles();
				for (int i = 0; i < filenumber; i++) {
					File fchild = fchilda[i];
					if (fchild.isFile()) {
						blret = fchild.delete();
						if (!blret) {
							break;
						}
					}
					else if (isRecurse) {
						blret = deleteFileDir(fchild.getAbsolutePath(), true);
					}
				}
			}
		}
		catch (Exception e) {
			blret = false;
		}

		return blret;
	}

	public static boolean deleteFileDir(String dirName, boolean isRecurse) {
		boolean blret = false;
		try {
			File f = new File(dirName);
			if (f == null || !f.exists()) {
				// Log.d(FILE_TAG, "file" + dirName + "not isExist");
				return false;
			}
			if (f.isFile()) {
				blret = f.delete();
				return blret;
			}
			else {
				File[] flst = f.listFiles();
				if (flst == null || flst.length <= 0) {
					f.delete();
					return true;
				}
				int filenumber = flst.length;
				File[] fchilda = f.listFiles();
				for (int i = 0; i < filenumber; i++) {
					File fchild = fchilda[i];
					if (fchild.isFile()) {
						blret = fchild.delete();
						if (!blret) {
							break;
						}
					}
					else if (isRecurse) {
						blret = deleteFileDir(fchild.getAbsolutePath(), true);
					}
				}

				// 删除当前文件夹
				blret = new File(dirName).delete();
			}
		}
		catch (Exception e) {
			// Log.d(FILE_TAG, e.getMessage());
			blret = false;
		}

		return blret;
	}

	/**
	 * 移动文件
	 * 
	 * @param filePath
	 */
	public static void removeToDir(String filePath, String toFilePath) {
		File file = new File(filePath);
		if (!file.exists()) {
			return;
		}
		file.renameTo(new File(toFilePath));
	}

}
