import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Compresses the files per Huffman encoding.
 * EECS 223, Prof. Lewicki
 * @author Ryan Rose
 */
public class HuffmanCoder {
	
	private String inputFile;
	
	private String outputFile;
	
	public HuffmanCoder(String inputFile, String outputFile){
		this.inputFile = inputFile;
		this.outputFile = outputFile;
	}
	
	public void compress() throws IOException{
		BinaryWriter writer = new BinaryWriter(outputFile);
		HuffmanCode hc = new HuffmanCode(inputFile);
		for(int i = 0; i < hc.originalList.size(); i++){
			writer.writeBinaryArray(hc.originalList.get(i).code);
		}
		writer.close();
	}
}
