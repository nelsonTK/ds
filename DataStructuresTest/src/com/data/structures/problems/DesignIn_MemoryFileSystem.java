package com.data.structures.problems;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * https://leetcode.com/problems/design-in-memory-file-system
 * HARD
 * @author Nelson Costa
 *
 */
public class DesignIn_MemoryFileSystem {

	static DesignIn_MemoryFileSystem d = new DesignIn_MemoryFileSystem();
	public static void main(String[] args) {
		String path = "/test/my/shoes";
		FileSystem obj = d.new FileSystem();
		List<String> param_1 = obj.ls(path);
		obj.mkdir(path);
		obj.addContentToFile(path, "content");
		String param_4 = obj.readContentFromFile(path);

	}

	/**
	 * @intuition
	 * 		What I tried to do was to create a N-ary tree using hashmaps
	 * 
	 * 		The file system has always a root
	 * 		
	 * 		and any new path you do add is appended to the root
	 * 
	 * 		And special care for the ls command that returns a file if it is a file.
	 * 
	 * 		also should take into account that split if has nothing in between separators puts an empty string there.
	 * 
	 * 		I Intentionally avoided using recursion
	 * 	
	 * 	@Optimizations
	 * 		1) I could have some cache, and invalidate cache on change
	 * 		2) Also believe I could improve space complexity O(1), if I had a sliding window to traverse the path variable
	 * 		
	 * 
	 * 
	 * @score
		 		Runtime: 57 ms, faster than 18.77% of Java online submissions for Design In-Memory File System.
				Memory Usage: 40.1 MB, less than 96.57% of Java online submissions for Design In-Memory File System.
	    
	    		Runtime: 41 ms, faster than 67.11% of Java online submissions for Design In-Memory File System.
				Memory Usage: 40.6 MB, less than 41.02% of Java online submissions for Design In-Memory File System.
	    @fail
	        1) null pointer, forgot initialize root
	        2) split returned empty space
	        3) forgot to append content to file
	        4) I didn't knew but it the pah is a file you should print the file name
	        
	     @time   
	        ls  				O(NLogN) where N is the number of files in the path
	        mkdir 				O(N) where N is the number of files in the path 
	        addcontentToFile 	O(N)
	        readFile 			O(N)
	        
	     @space
	        ls					O(N)
	        mkdir				O(N)
	        addcontentTofile	O(N)
	        ReadFile			O(N)

	 **/
	class FileSystem {
		File root;
		//could have some Cache


		public FileSystem() {
			root = new File("/");
		}


		//O(NLogN)
		public List<String> ls(String path) {
			List<String> fileNames = new ArrayList<>();

			File file = getFile(path);

			if (isFile(file))
			{
				fileNames.add(file.name);
			}
			else
			{
				for (String s : file.files.keySet())
					fileNames.add(s);

				Collections.sort(fileNames);
			}
			return fileNames;
		}


		//O(N)
		public void mkdir(String path) {
			makeFile(path);        
		}


		//O(N)
		public void addContentToFile(String path, String content) {
			File file = makeFile(path);

			if (!isFile(file))
				file.type = FileType.File;

			file.content.append(content);
		}


		//O(N)
		public String readContentFromFile(String path) {
			File file = getFile(path);
			return file.content.toString();
		}


		//gets a given file path
		private File getFile(String path){

			if (path.equals("/"))
			{
				return root;
			}

			String [] fileNames = path.split("/");

			File node = root;
			String file;
			for (int i = 1; i < fileNames.length; i++)
			{
				file = fileNames[i];
				node = node.files.get(file);
			}

			return node;
		}


		//creates a file whether it is a directory or a file
		//O(N) N is the size of the path
		private File makeFile(String path){

			if (path.equals("/"))
			{
				return root;
			}

			String [] fileNames = path.split("/"); 
			File node = root;
			String fileName;         
			File children;

			for (int i = 1; i < fileNames.length; i++)
			{
				fileName = fileNames[i];

				if (node.files.containsKey(fileName))
				{
					node = node.files.get(fileName);
				}
				else
				{
					children = new File(fileName);
					node.files.put(fileName, children);
					node = children;
				}
			}
			return node;
		}


		private boolean isFile(File file){
			return file.type.equals(FileType.File);
		}

		class File {
			String name;
			StringBuilder content;
			HashMap<String, File> files;
			FileType type;

			public File(String n){
				name = n;
				files = new HashMap<>();
				content = new StringBuilder("");
				type =  FileType.Directory;
			}

			public File(String n, String c){
				name = n;
				this.content = new StringBuilder(c);
				files = new HashMap<>();
				type =  FileType.File;
			}
		}
	}

	enum FileType
	{
		File, Directory;
	}
}

/**
 * Solution homologoes to mine
 * @author Nelson Costa
 *
 */
class FileSystemSolution1 {
    class File {
        boolean isfile = false;
        HashMap < String, File > files = new HashMap < > ();
        String content = "";
    }
    File root;
    public FileSystemSolution1() {
        root = new File();
    }

    public List < String > ls(String path) {
        File t = root;
        List < String > files = new ArrayList < > ();
        if (!path.equals("/")) {
            String[] d = path.split("/");
            for (int i = 1; i < d.length; i++) {
                t = t.files.get(d[i]);
            }
            if (t.isfile) {
                files.add(d[d.length - 1]);
                return files;
            }
        }
        List < String > res_files = new ArrayList < > (t.files.keySet());
        Collections.sort(res_files);
        return res_files;
    }

    public void mkdir(String path) {
        File t = root;
        String[] d = path.split("/");
        for (int i = 1; i < d.length; i++) {
            if (!t.files.containsKey(d[i]))
                t.files.put(d[i], new File());
            t = t.files.get(d[i]);
        }
    }

    public void addContentToFile(String filePath, String content) {
        File t = root;
        String[] d = filePath.split("/");
        for (int i = 1; i < d.length - 1; i++) {
            t = t.files.get(d[i]);
        }
        if (!t.files.containsKey(d[d.length - 1]))
            t.files.put(d[d.length - 1], new File());
        t = t.files.get(d[d.length - 1]);
        t.isfile = true;
        t.content = t.content + content;
    }

    public String readContentFromFile(String filePath) {
        File t = root;
        String[] d = filePath.split("/");
        for (int i = 1; i < d.length - 1; i++) {
            t = t.files.get(d[i]);
        }
        return t.files.get(d[d.length - 1]).content;
    }
}


/**
 * Similar concepts, but with small differences.
 * 1) here the node has methods to add and get child node
 * 2) instead of spliting string it parses the substring..
 * 
 * @author Nelson Costa
 *
 */
class FileSystemUnoficialSolution1 {
    
    class FsNode {
        String name;
        boolean isDir;
        List<String> nameList;
        Map<String, FsNode> fsMap;
        String content;
        public FsNode (String name, boolean isDir) {
            this.name = name;
            this.isDir = isDir;
            if (isDir) {
                nameList = new ArrayList<> ();
                fsMap = new HashMap<> ();
            } else {
                content = "";
            }
        }
        public void addContent (String content) {
            if (this.isDir) return;
            this.content = this.content + content;
        }
        public void addNode (FsNode fn) {
            if (!this.isDir || fsMap.containsKey(fn.name)) return;
            nameList.add(fn.name);
            fsMap.put(fn.name, fn);
        }
        public FsNode getChildNode (String name) {
            if (!this.isDir || !fsMap.containsKey(name)) return null;
            return fsMap.get(name);
        }
    }

    //root directory
    FsNode root;
    
    public FileSystemUnoficialSolution1() {
        this.root = new FsNode("root", true);
    }
    
    public List<String> ls(String path) {
        return lsDir(path, root);
    }
    
    private List<String> lsDisplay (FsNode fn) {
        if (!fn.isDir) {
            List<String> res = new ArrayList<> ();
            res.add(fn.name);
            return res;
        }
        Collections.sort(fn.nameList);
        return fn.nameList;
    }
        
    //sitting in current dir, list the path.
    private List<String> lsDir(String path, FsNode fn) {
        if (path.length() <= 1) return lsDisplay(fn);
        int i = 1;
        while (i < path.length()) {
            if (path.charAt(i) == '/') break;
            i++;
        }
        FsNode child = fn.getChildNode(path.substring(1, i));
        if (child != null) return lsDir(path.substring(i), child);
        return null;
    }
    
    public void mkdir(String path) {
        mkdirDir(path, root);
    }
        
    private void mkdirDir(String path, FsNode fn) {
        if (path.length() <= 1) return;
        int i = 1;
        while (i < path.length()) {
            if (path.charAt(i) == '/') break;
            i++;
        }
        FsNode dir = fn.getChildNode(path.substring(1, i));
        if (dir == null) {
            dir = new FsNode(path.substring(1, i), true);
            fn.addNode(dir);
        } 
        mkdirDir(path.substring(i), dir);
    }
    
    public void addContentToFile(String filePath, String content) {
        addContentDir(filePath, content, root);
    }
    
    private void addContentDir(String path, String content, FsNode fn) {
        if (path.length() <= 1 || !fn.isDir) {
            fn.addContent(content);
            return;
        }
        int i = 1;
        while (i < path.length()) {
            if (path.charAt(i) == '/') break;
            i++;
        }
        FsNode nfn = fn.getChildNode(path.substring(1, i));
        if (nfn == null) {
            nfn = new FsNode(path.substring(1, i), false);
            fn.addNode(nfn);
        } 
        addContentDir(path.substring(i), content, nfn);
    }
    
    public String readContentFromFile(String filePath) {
        return readContentDir(filePath, root);
    }
    
    private String readContentDir(String path, FsNode fn) {
        if (path.length() <= 1 && !fn.isDir) {
            return fn.content;
        }
        int i = 1;
        while (i < path.length()) {
            if (path.charAt(i) == '/') break;
            i++;
        }
        FsNode nfn = fn.getChildNode(path.substring(1, i));
        if (nfn == null) {
            return null;
        } 
        return readContentDir(path.substring(i), nfn);
    }
}
