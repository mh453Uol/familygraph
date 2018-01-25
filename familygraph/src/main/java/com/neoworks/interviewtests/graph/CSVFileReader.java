package com.neoworks.interviewtests.graph;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class CSVFileReader {
	private String path;
	
	public ArrayList<String> Read(String path) {
		
		ArrayList<String> data = new ArrayList<String>();

		try(BufferedReader br = new BufferedReader(new FileReader(path))){
			String line = "";
			while((line = br.readLine()) != null) {
				data.add(line);
			}
		} catch (FileNotFoundException fe) {
			System.out.println("File cant be found path given:" + path);
			fe.printStackTrace();
		} catch (IOException e) {
			System.out.println("Failed or interrupted I/O operations");
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}
}
