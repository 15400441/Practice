package threadTest;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;


public class ForkJoinTest {
	 private final ForkJoinPool forkJoinPool = new ForkJoinPool();
	 
	public static void main(String[] args) throws IOException {
		 ForkJoinTest forkJoinTest=new ForkJoinTest();
         String searchedWord="mobile";
         String dir="D:/svnProject/hc-ionic/trunk/src";
         Folder folder=Folder.fromDirectory(new File(dir));
		
		 Long count=forkJoinTest.countOccurrencesInParallel(folder, searchedWord);
		 //Long count=forkJoinTest.countOccurrencesOnSingleThread(folder, searchedWord);
		 System.out.println("search dir:"+dir);
		 System.out.println("search word:"+searchedWord+" | count:"+count);
		 
		
	}
	
	
	    

	    Long countOccurrencesInParallel(Folder folder, String searchedWord) {
	        return forkJoinPool.invoke(new FolderSearchTask(folder, searchedWord));
	    }
	    
	    
	    Long countOccurrencesOnSingleThread(Folder folder, String searchedWord) {
		    long count = 0;
		    for (Folder subFolder : folder.getSubFolders()) {
		        count = count + countOccurrencesOnSingleThread(subFolder, searchedWord);
		    }
		    for (Document document : folder.getDocuments()) {
		        count = count + new WordCounter().occurrencesCount(document, searchedWord);
		    }
		    return count;
		}
	
}


class Document{
	private final List<String> lines;
	
	private String name="";
	
	 Document(List<String> lines,String name) {
		this.lines=lines;
		this.name=name;
	}
	 
	 public String getName() {
		return name;
	}
	 
	 List<String> getLines() {
	        return this.lines;
	    }
	 
	 static Document fromFile(File file) throws IOException{
		 
		 List<String> lines=new LinkedList<>();
		 try(BufferedReader reader=new BufferedReader(new FileReader(file))){
			 String line=reader.readLine();
			 while(line !=null){
				 lines.add(line);
				 line=reader.readLine();
			 }
		 }
		 
		 return new Document(lines,file.getName());
	 }
}


class Folder{
	private final List<Folder> subFolders;
	private final List<Document> documents;
	
	public Folder(List<Folder> subFolders,List<Document> documents) {
		this.subFolders=subFolders;
		this.documents=documents;
	}

	public List<Folder> getSubFolders() {
		return subFolders;
	}

	public List<Document> getDocuments() {
		return documents;
	}
	
	static Folder fromDirectory(File dir) throws IOException{
		List<Document> documents=new LinkedList<>();
		List<Folder> subFolders=new LinkedList<>();
		for(File entry:dir.listFiles()){
			if(entry.isDirectory()){
				subFolders.add(Folder.fromDirectory(entry));
			}else{
				documents.add(Document.fromFile(entry));
			}
		}
		
		return new Folder(subFolders, documents);
		
	}
}



class WordCounter{
	String[] wordsIn(String line) {
        return line.trim().split("(\\s|\\p{Punct})+");
    }
    
    Long occurrencesCount(Document document, String searchedWord) {
        long count = 0;
        for (String line : document.getLines()) {
            for (String word : wordsIn(line)) {
                if (searchedWord.equals(word)) {
                    count = count + 1;
                    System.out.println(document.getName());
                    System.out.println(line);
                    System.out.println("********************************************************************");
                }
            }
        }
        return count;
    }
    
    
    
   
	
}





class DocumentSearchTask extends RecursiveTask<Long>{
	private final Document document;
    private final String searchedWord;
    
    DocumentSearchTask(Document document, String searchedWord) {
        super();
        this.document = document;
        this.searchedWord = searchedWord;
    }
    
    @Override
    protected Long compute() {
        return new WordCounter().occurrencesCount(document, searchedWord);
    }
	
}


class FolderSearchTask extends RecursiveTask<Long>{
	private final Folder folder;
    private final String searchedWord;
    
    FolderSearchTask(Folder folder, String searchedWord) {
        super();
        this.folder = folder;
        this.searchedWord = searchedWord;
    }
	
	@Override
	protected Long compute() {
		long count=0;
		List<RecursiveTask<Long>> forks=new LinkedList<>();
		
		for(Folder subFolder:folder.getSubFolders()){
			FolderSearchTask task=new FolderSearchTask(subFolder, searchedWord);
			forks.add(task);
			task.fork();
		}
		
		for (Document document : folder.getDocuments()) {
            DocumentSearchTask task = new DocumentSearchTask(document, searchedWord);
            forks.add(task);
            task.fork();
        }
		
		for (RecursiveTask<Long> task : forks) {
            count = count + task.join();
        }
		
        return count;
		
		
	}
	
}