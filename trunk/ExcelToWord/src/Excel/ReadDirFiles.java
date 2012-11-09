package Excel;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ReadDirFiles {
	public ReadDirFiles() {
	}

	public static boolean deletefile(String delpath) throws FileNotFoundException, IOException {
		try {
			File file = new File(delpath);
			if (!file.isDirectory()) {
				System.out.println("1");
				file.delete();
			} else if (file.isDirectory()) {
				String[] filelist = file.list();
				for (int i = 0; i < filelist.length; i++) {
					File delfile = new File(delpath + "\\" + filelist[i]);
					if (!delfile.isDirectory()) {
						System.out.println("absolutepath=" + delfile.getAbsolutePath());
						delfile.delete();
						System.out.println("删除文件成功");
					} else if (delfile.isDirectory()) {
						deletefile(delpath + "\\" + filelist[i]);
					}
				}
				file.delete();
			}
		} catch (FileNotFoundException e) {
			System.out.println("deletefile()   Exception:" + e.getMessage());
		}
		return true;
	}

	public static boolean readfile(String filepath) throws FileNotFoundException, IOException {
		try {
			File file = new File(filepath);
			if (!file.isDirectory()) {
				System.out.println("文件");
				System.out.println("path=" + file.getPath());
				System.out.println("absolutepath=" + file.getAbsolutePath());
				System.out.println("name=" + file.getName());

			} else if (file.isDirectory()) {
				System.out.println("文件夹");
				int num = 0;
				String[] filelist = file.list();
				for (int i = 0; i < filelist.length; i++) {
					File readfile = new File(filepath + "\\" + filelist[i]);
					if (!readfile.isDirectory()) {
						System.out.println("absolutepath="+ readfile.getAbsolutePath());
					} else if (readfile.isDirectory()) {
						readfile(filepath + "\\" + filelist[i]);
					}
					num++;
				}
				System.out.println("文件夹中文件个数："+num);
			}
		} catch (FileNotFoundException e) {
			System.out.println("readfile()   Exception:" + e.getMessage());
		}
		return true;
	}

	public ArrayList getFileList(String filepath) throws FileNotFoundException, IOException {
		ArrayList resultList = null;
		int num = 0;
		try {
			File file = new File(filepath);
			if (!file.isDirectory()) {
				System.out.println("文件");
			} else if (file.isDirectory()) {
				resultList = new ArrayList();
				System.out.println("文件夹");
				String[] filelist = file.list();
				for (int i = 0; i < filelist.length; i++) {
					File readfile = new File(filepath + "\\" + filelist[i]);
					if (!readfile.isDirectory()) {
						resultList.add(readfile.getAbsolutePath());
						System.out.println("absolutepath="+ readfile.getAbsolutePath());
					} else if (readfile.isDirectory()) {
						getFileList(filepath + "\\" + filelist[i]);
					}
					num++;
				}
			}
		} catch (FileNotFoundException e) {
		}
//		System.out.println("文件夹中文件个数："+num+","+resultList.size());
		return resultList;
	}
	
	public static void main(String[] args) {
		try {
			readfile("E:/temp/成果获奖");
			// deletefile("E:/data");
		} catch (FileNotFoundException ex) {
		} catch (IOException ex) {
		}
	}

}