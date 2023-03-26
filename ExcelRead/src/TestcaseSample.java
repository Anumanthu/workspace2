package src;

import java.util.ArrayList;

public class TestcaseSample {

    public static void main(String[] args) throws Exception {
		
		
/*
		ExcelReading rd=new ExcelReading();
		
		
		ArrayList<String> a=rd.getData("testcases");
		
		a.size();
		
		for(int i=0;i<a.size();i++)
			
		{
			System.out.println(a.get(i));
		}
		
		
*/
        ReadExeclData rd = new ReadExeclData();

        ArrayList<String> a = rd.getData("FIRSTXCELNAME1", "Purchase");

        a.size();

        for (int i = 0; i < a.size(); i++) {
            System.out.println(a.get(i));
        }


    }

}
