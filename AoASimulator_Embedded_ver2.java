import mo.edu.ipm.esap.kon.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
public class AoASimulator_Embedded_ver2 {
	static double s1x, s1y, s2x, s2y, s3x, s3y, s4x, s4y, p1x, p1y;
	public static void main(String[] args) {
		//double	demo_s1x, demo_s1y, demo_s2x, demo_s2y, demo_s3x, demo_s3y, demo_s4x, demo_s4y, demo_p1x, demo_p1y,
		//		x_starting_x, x_starting_y, x_ending_x, x_ending_y, y_starting_x, y_starting_y, y_ending_x, y_ending_y;
		
		File class_f1, class_f2, class_f3, img_aoa_draw, data_from_Kon;
		String single;
		String[] input_data_separate = new String[18];
		char[] input_data_temp;
		int count_input_data_1 = 0;
		int count_input_data_2 = 0;
		int this_word_num, count_input_data_temp;
		
		String line = null;
		String input_data = "";
		try {
            FileReader fileReader = new FileReader("data_from_GUI.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while((line = bufferedReader.readLine()) != null) {
                input_data = line;
            }
            bufferedReader.close();         
        }
		catch(FileNotFoundException ex) {
            System.out.println("Unable to open file.");                
        }
        catch(IOException ex) {
            System.out.println("Error reading file.");
        }
		int input_data_size = input_data.length();
		
		Kon.connect();
		
		
		
		for(int i = 0; i < 18; i++) {
			this_word_num = 0;
			char char_token;
			while(count_input_data_1 < input_data_size) {
				char_token = input_data.charAt(count_input_data_1);
				if(char_token != ',' && char_token != '!') {
					this_word_num++;
					count_input_data_1++;
				}
				else {
					break;
				}
			}
			count_input_data_1 += 2;
			count_input_data_temp = 0;
			input_data_temp = new char[this_word_num];
			single = "";
			while(count_input_data_2 < input_data_size) {
				char_token = input_data.charAt(count_input_data_2);
				if(char_token != ',' && char_token != '!') {
					input_data_temp[count_input_data_temp] = char_token;
					count_input_data_temp++;
					count_input_data_2++;
				}
				else {
					break;
				}
			}
			count_input_data_2 += 2;
			
			for(char u : input_data_temp) {
				single = single + u;
			}
			input_data_separate[i] = single;
		}
		
		double[] coordinate = new double[10];
		boolean[] random_on_off = new boolean[4];
		int[] random_no = new int[4];
		for(int i = 0; i < 10; i++) {
			coordinate[i] = Double.parseDouble(input_data_separate[i]);
		}
		for(int i = 0, j = 10; i < 4; i++, j++) {
			random_on_off[i] = Boolean.parseBoolean(input_data_separate[j]);
		}
		for(int i = 0, j = 14; i < 4; i++, j++) {
			random_no[i] = Integer.parseInt(input_data_separate[j]);
		}
		
		/*
		//Instruction image
		demo_s1x = 300;
		demo_s1y = 90;
		demo_s2x = 70;
		demo_s2y = 70;
		demo_s3x = 60;
		demo_s3y = 360;
		demo_s4x = 370;
		demo_s4y = 320;
		demo_p1x = 230;
		demo_p1y = 230;
		x_starting_x = 30;
		x_starting_y = 30;
		x_ending_x = 400;
		x_ending_y = 30;
		y_starting_x = 30;
		y_starting_y = 30;
		y_ending_x = 30;
		y_ending_y = 420;
		
		//Draw instruction image
		//Draw x, y axes
		Kon.drawLine(x_starting_x, x_starting_y, x_ending_x, x_ending_y);
		Kon.startPath(x_ending_x, x_ending_y);
		Kon.lineToPath(x_ending_x - 3, x_ending_y - 3);
		Kon.lineToPath(x_ending_x + 3, x_ending_y);
		Kon.lineToPath(x_ending_x - 3, x_ending_y + 3);
		Kon.closePath();
		Kon.fillPath();
		Kon.drawPath();
		Kon.drawLine(y_starting_x, y_starting_y, y_ending_x, y_ending_y);
		Kon.startPath(y_ending_x, y_ending_y);
		Kon.lineToPath(y_ending_x + 3, y_ending_y - 3);
		Kon.lineToPath(y_ending_x, y_ending_y + 3);
		Kon.lineToPath(y_ending_x - 3, y_ending_y - 3);
		Kon.closePath();
		Kon.fillPath();
		Kon.drawPath();
		Kon.drawString("x", x_ending_x - 12, x_ending_y - 8);
		Kon.drawString("y", y_ending_x - 12, y_ending_y - 10);
		
		Kon.drawCircle(demo_s1x, demo_s1y, 5);
		Kon.drawLine(demo_s1x - 5, 
				demo_s1y + 5 + Math.sqrt(10 * 10 - 5 * 5), 
				demo_s1x + 5, 
				demo_s1y + 5 + Math.sqrt(10 * 10 - 5 * 5));
		Kon.drawLine(demo_s1x, demo_s1y + 5, demo_s1x - 5, demo_s1y + 5 + Math.sqrt(10 * 10 - 5 * 5));
		Kon.drawLine(demo_s1x, demo_s1y + 5, demo_s1x + 5, demo_s1y + 5 + Math.sqrt(10 * 10 - 5 * 5));
		Kon.drawLine(demo_s1x - (5 * 0.4), 
				demo_s1y + 5 + (Math.sqrt(10 * 10 - 5 * 5) * 0.4), 
				demo_s1x + (5 * 0.4), 
				demo_s1y + 5 + (Math.sqrt(10 * 10 - 5 * 5) * 0.4));
		Kon.drawLine(demo_s1x - (5 * 0.7), 
				demo_s1y + 5 + (Math.sqrt(10 * 10 - 5 * 5) * 0.7), 
				demo_s1x + (5 * 0.7), 
				demo_s1y + 5 + (Math.sqrt(10 * 10 - 5 * 5) * 0.7));
		Kon.drawArc(demo_s1x - 5 - 2, demo_s1y - 5 - 2, 14, 14, 50, 80);
		Kon.drawArc(demo_s1x - 5 - 2 - 2, demo_s1y - 5 - 2 - 2, 18, 18, 45, 90);
		
		Kon.drawCircle(demo_s2x, demo_s2y, 5);
		Kon.drawLine(demo_s2x - 5, 
				demo_s2y + 5 + Math.sqrt(10 * 10 - 5 * 5), 
				demo_s2x + 5, 
				demo_s2y + 5 + Math.sqrt(10 * 10 - 5 * 5));
		Kon.drawLine(demo_s2x, demo_s2y + 5, demo_s2x - 5, demo_s2y + 5 + Math.sqrt(10 * 10 - 5 * 5));
		Kon.drawLine(demo_s2x, demo_s2y + 5, demo_s2x + 5, demo_s2y + 5 + Math.sqrt(10 * 10 - 5 * 5));
		Kon.drawLine(demo_s2x - (5 * 0.4), 
				demo_s2y + 5 + (Math.sqrt(10 * 10 - 5 * 5) * 0.4), 
				demo_s2x + (5 * 0.4), 
				demo_s2y + 5 + (Math.sqrt(10 * 10 - 5 * 5) * 0.4));
		Kon.drawLine(demo_s2x - (5 * 0.7), 
				demo_s2y + 5 + (Math.sqrt(10 * 10 - 5 * 5) * 0.7), 
				demo_s2x + (5 * 0.7), 
				demo_s2y + 5 + (Math.sqrt(10 * 10 - 5 * 5) * 0.7));
		Kon.drawArc(demo_s2x - 5 - 2, demo_s2y - 5 - 2, 14, 14, 50, 80);
		Kon.drawArc(demo_s2x - 5 - 2 - 2, demo_s2y - 5 - 2 - 2, 18, 18, 45, 90);
		
		Kon.drawCircle(demo_s3x, demo_s3y, 5);
		Kon.drawLine(demo_s3x - 5, 
				demo_s3y + 5 + Math.sqrt(10 * 10 - 5 * 5), 
				demo_s3x + 5, 
				demo_s3y + 5 + Math.sqrt(10 * 10 - 5 * 5));
		Kon.drawLine(demo_s3x, demo_s3y + 5, demo_s3x - 5, demo_s3y + 5 + Math.sqrt(10 * 10 - 5 * 5));
		Kon.drawLine(demo_s3x, demo_s3y + 5, demo_s3x + 5, demo_s3y + 5 + Math.sqrt(10 * 10 - 5 * 5));
		Kon.drawLine(demo_s3x - (5 * 0.4), 
				demo_s3y + 5 + (Math.sqrt(10 * 10 - 5 * 5) * 0.4), 
				demo_s3x + (5 * 0.4), 
				demo_s3y + 5 + (Math.sqrt(10 * 10 - 5 * 5) * 0.4));
		Kon.drawLine(demo_s3x - (5 * 0.7), 
				demo_s3y + 5 + (Math.sqrt(10 * 10 - 5 * 5) * 0.7), 
				demo_s3x + (5 * 0.7), 
				demo_s3y + 5 + (Math.sqrt(10 * 10 - 5 * 5) * 0.7));
		Kon.drawArc(demo_s3x - 5 - 2, demo_s3y - 5 - 2, 14, 14, 50, 80);
		Kon.drawArc(demo_s3x - 5 - 2 - 2, demo_s3y - 5 - 2 - 2, 18, 18, 45, 90);
		
		Kon.drawCircle(demo_s4x, demo_s4y, 5);
		Kon.drawLine(demo_s4x - 5, 
				demo_s4y + 5 + Math.sqrt(10 * 10 - 5 * 5), 
				demo_s4x + 5, 
				demo_s4y + 5 + Math.sqrt(10 * 10 - 5 * 5));
		Kon.drawLine(demo_s4x, demo_s4y + 5, demo_s4x - 5, demo_s4y + 5 + Math.sqrt(10 * 10 - 5 * 5));
		Kon.drawLine(demo_s4x, demo_s4y + 5, demo_s4x + 5, demo_s4y + 5 + Math.sqrt(10 * 10 - 5 * 5));
		Kon.drawLine(demo_s4x - (5 * 0.4), 
				demo_s4y + 5 + (Math.sqrt(10 * 10 - 5 * 5) * 0.4), 
				demo_s4x + (5 * 0.4), 
				demo_s4y + 5 + (Math.sqrt(10 * 10 - 5 * 5) * 0.4));
		Kon.drawLine(demo_s4x - (5 * 0.7), 
				demo_s4y + 5 + (Math.sqrt(10 * 10 - 5 * 5) * 0.7), 
				demo_s4x + (5 * 0.7), 
				demo_s4y + 5 + (Math.sqrt(10 * 10 - 5 * 5) * 0.7));
		Kon.drawArc(demo_s4x - 5 - 2, demo_s4y - 5 - 2, 14, 14, 50, 80);
		Kon.drawArc(demo_s4x - 5 - 2 - 2, demo_s4y - 5 - 2 - 2, 18, 18, 45, 90);
		
		Kon.ellipsePath(demo_p1x - 6, demo_p1y - 8, 16, 10);
		Kon.rotatePath(-45, demo_p1x - 6 + 8, demo_p1y - 8 + 5);
		Kon.drawPath();
		Kon.drawLine(demo_p1x - 6 + 8, demo_p1y - 8 + 5, demo_p1x - 6 + 8 + 5, demo_p1y - 8 + 5 - 4);
		Kon.drawCircle(demo_p1x - 6 + 8 + 5 + 2, demo_p1y - 8 + 5 - 4 - 1.75, 2);
		Kon.drawLine(demo_p1x - 6 + 8 - 1, demo_p1y - 8 + 5 + 6, demo_p1x - 6 + 8 - 1, demo_p1y - 8 + 5 + 6 + 6);
		Kon.drawLine(demo_p1x - 6 + 8 + 1, demo_p1y - 8 + 5 + 7, demo_p1x - 6 + 8 + 1, demo_p1y - 8 + 5 + 7 + 5);
		Kon.drawLine(demo_p1x - 6 + 8 - 1 - 4, demo_p1y - 8 + 5 + 12, demo_p1x - 6 + 8 + 1 + 4, demo_p1y - 8 + 5 + 12);
		Kon.drawArc(demo_p1x - 6 + 8 + 5 + 2 + 0.5, demo_p1y - 8 + 5 - 4 - 1.75 - 18.5, 18, 18, 180, 90);
		Kon.drawArc(demo_p1x - 6 + 8 + 5 + 2 + 0.5 + 2, demo_p1y - 8 + 5 - 4 - 1.75 - 18.5 + 2, 14, 14, 185, 80);
		
		Kon.setStroke(StrokeAttribute.DASHED);
		Kon.setColor(ColorName.LIGHTGRAY);
		Kon.drawArc(140, 140, 150, 150, 45, 270);
		//Kon.drawCircle(215 + Math.cos(Math.toRadians(45)) * 75, 215 + Math.sin(Math.toRadians(45)) * 75, 3);
		Kon.startPath(210 + Math.cos(Math.toRadians(45)) * 75, 210 + Math.sin(Math.toRadians(45)) * 75);
		Kon.lineToPath(220 + Math.cos(Math.toRadians(45)) * 75, 220 + Math.sin(Math.toRadians(45)) * 75);
		Kon.lineToPath(220 + Math.cos(Math.toRadians(45)) * 75, 210 + Math.sin(Math.toRadians(45)) * 75);
		Kon.closePath();
		Kon.fillPath();
		Kon.drawPath();
		
		Kon.setStroke(StrokeAttribute.SOLID);
		Kon.setColor(ColorName.BLACK);
		Kon.drawString("s1 (x,y)", demo_s1x - 20, demo_s1y + 30);
		Kon.drawString("s2 (x,y)", demo_s2x - 20, demo_s2y + 30);
		Kon.drawString("s3 (x,y)", demo_s3x - 20, demo_s3y + 30);
		Kon.drawString("s4 (x,y)", demo_s4x - 20, demo_s4y + 30);
		Kon.drawString("p1 (x,y)", demo_p1x - 20, demo_p1y + 30);
		
		Kon.refresh();
		*/
		
		//Variables declaration
		Antenna trs, tls, bls, brs;
		Phone p1;
		double tltr_brtr_mid_x = 0;
		double tltr_brtr_mid_y = 0;
		double tltr_tlbl_mid_x = 0;
		double tltr_tlbl_mid_y = 0;
		double brbl_brtr_mid_x = 0;
		double brbl_brtr_mid_y = 0;
		double brbl_tlbl_mid_x = 0;
		double brbl_tlbl_mid_y = 0;
		double intersection_m1m2_x = 0;
		double intersection_m1m2_y = 0;
		double m1pa, m1pb, m1pc, m1pd, m2pa, m2pb, m2pc, m2pd, m1A, m1B, m1C, m2A, m2B, m2C;
		double	intersection_tltr_x, intersection_tltr_y, intersection_tlbl_x, intersection_tlbl_y,
				intersection_brtr_x, intersection_brtr_y, intersection_brbl_x, intersection_brbl_y;
		
		
		//Create antenna and phone objects
		trs = new Antenna(coordinate[0], coordinate[1], coordinate[8], coordinate[9], "trs");
		tls = new Antenna(coordinate[2], coordinate[3], coordinate[8], coordinate[9], "tls");
		bls = new Antenna(coordinate[4], coordinate[5], coordinate[8], coordinate[9], "bls");
		brs = new Antenna(coordinate[6], coordinate[7], coordinate[8], coordinate[9], "brs");
		p1 = new Phone(coordinate[8], coordinate[9]);
		
		
		if(random_on_off[0] == true && random_no[0] != 2100000000) {
			trs.setRandomNo(random_no[0]);
		}
		if(random_on_off[1] == true && random_no[1] != 2100000000) {
			tls.setRandomNo(random_no[1]);
		}
		if(random_on_off[2] == true && random_no[2] != 2100000000) {
			bls.setRandomNo(random_no[2]);
		}
		if(random_on_off[3] == true && random_no[3] != 2100000000) {
			brs.setRandomNo(random_no[3]);
		}
		
		
		//Calculations
		trs.run_phase1();
		tls.run_phase1();
		bls.run_phase1();
		brs.run_phase1();
		
		
		
		
		//Calculate intersections
		intersection_tltr_x = (tls.leC * trs.leB - tls.leB * trs.leC)/(tls.leA * trs.leB - tls.leB * trs.leA);
		intersection_tltr_y = (tls.leA * trs.leC - tls.leC * trs.leA)/(tls.leA * trs.leB - tls.leB * trs.leA);
		intersection_tlbl_x = (tls.leC * bls.leB - tls.leB * bls.leC)/(tls.leA * bls.leB - tls.leB * bls.leA);
		intersection_tlbl_y = (tls.leA * bls.leC - tls.leC * bls.leA)/(tls.leA * bls.leB - tls.leB * bls.leA);
		intersection_brtr_x = (trs.leC * brs.leB - trs.leB * brs.leC)/(trs.leA * brs.leB - trs.leB * brs.leA);
		intersection_brtr_y = (trs.leA * brs.leC - trs.leC * brs.leA)/(trs.leA * brs.leB - trs.leB * brs.leA);
		intersection_brbl_x = (bls.leC * brs.leB - bls.leB * brs.leC)/(bls.leA * brs.leB - bls.leB * brs.leA);
		intersection_brbl_y = (bls.leA * brs.leC - bls.leC * brs.leA)/(bls.leA * brs.leB - bls.leB * brs.leA);
		/*
		System.out.println(tls.leA + " " + tls.leB + " " + tls.leC);
		System.out.println(bls.leA + " " + bls.leB + " " + bls.leC);
		System.out.println(intersection_tltr_x + " " + intersection_tlbl_x);
		System.out.println(intersection_tlbl_x + " " + intersection_brtr_x);
		System.out.println(intersection_brtr_x + " " + intersection_brbl_x);
		System.out.println(intersection_tltr_y + " " + intersection_tlbl_y);
		System.out.println(intersection_tlbl_y + " " + intersection_brtr_y);
		System.out.println(intersection_brtr_y + " " + intersection_brbl_y);
		*/
		
		//Round numbers
		intersection_tltr_x = Double.parseDouble(String.format("%.3f", intersection_tltr_x));
		intersection_tltr_y = Double.parseDouble(String.format("%.3f", intersection_tltr_y));
		intersection_tlbl_x = Double.parseDouble(String.format("%.3f", intersection_tlbl_x));
		intersection_tlbl_y = Double.parseDouble(String.format("%.3f", intersection_tlbl_y));
		intersection_brtr_x = Double.parseDouble(String.format("%.3f", intersection_brtr_x));
		intersection_brtr_y = Double.parseDouble(String.format("%.3f", intersection_brtr_y));
		intersection_brbl_x = Double.parseDouble(String.format("%.3f", intersection_brbl_x));
		intersection_brbl_y = Double.parseDouble(String.format("%.3f", intersection_brbl_y));
		/*
		// + " " + 
		System.out.println(intersection_tltr_x == intersection_tlbl_x);
		System.out.println(intersection_tlbl_x == intersection_brtr_x);
		System.out.println(intersection_brtr_x == intersection_brbl_x);
		System.out.println(intersection_tltr_y == intersection_tlbl_y);
		System.out.println(intersection_tlbl_y == intersection_brtr_y);
		System.out.println(intersection_brtr_y == intersection_brbl_y);
		*/
		
		
		//Calculate midpoints
		if(	intersection_tltr_x == intersection_tlbl_x &&
				intersection_tlbl_x == intersection_brtr_x &&
				intersection_brtr_x == intersection_brbl_x &&
				intersection_tltr_y == intersection_tlbl_y &&
				intersection_tlbl_y == intersection_brtr_y &&
				intersection_brtr_y == intersection_brbl_y	)
			{
				intersection_m1m2_x = intersection_tltr_x;
				intersection_m1m2_y = intersection_tltr_y;
			}
			else if(	Math.abs(Double.parseDouble(String.format("%.6f", trs.leA))) ==
						Math.abs(Double.parseDouble(String.format("%.6f", tls.leA))) &&
						Math.abs(Double.parseDouble(String.format("%.6f", trs.leB))) ==
						Math.abs(Double.parseDouble(String.format("%.6f", tls.leB))) &&
						Math.abs(Double.parseDouble(String.format("%.6f", trs.leC))) ==
						Math.abs(Double.parseDouble(String.format("%.6f", tls.leC))))
			{
				intersection_m1m2_x = (intersection_brtr_x + intersection_tlbl_x)/2;
				intersection_m1m2_y = (intersection_brtr_y + intersection_tlbl_y)/2;
			}
			else if(	Math.abs(Double.parseDouble(String.format("%.6f", trs.leA))) ==
						Math.abs(Double.parseDouble(String.format("%.6f", brs.leA))) &&
						Math.abs(Double.parseDouble(String.format("%.6f", trs.leB))) ==
						Math.abs(Double.parseDouble(String.format("%.6f", brs.leB))) &&
						Math.abs(Double.parseDouble(String.format("%.6f", trs.leC))) ==
						Math.abs(Double.parseDouble(String.format("%.6f", brs.leC))))
			{
				intersection_m1m2_x = (intersection_brbl_x + intersection_tltr_x)/2;
				intersection_m1m2_y = (intersection_brbl_y + intersection_tltr_y)/2;
			}
			else if(	Math.abs(Double.parseDouble(String.format("%.6f", tls.leA))) ==
						Math.abs(Double.parseDouble(String.format("%.6f", bls.leA))) &&
						Math.abs(Double.parseDouble(String.format("%.6f", tls.leB))) ==
						Math.abs(Double.parseDouble(String.format("%.6f", bls.leB))) &&
						Math.abs(Double.parseDouble(String.format("%.6f", tls.leC))) ==
						Math.abs(Double.parseDouble(String.format("%.6f", bls.leC))))
			{
				intersection_m1m2_x = (intersection_brbl_x + intersection_tltr_x)/2;
				intersection_m1m2_y = (intersection_brbl_y + intersection_tltr_y)/2;
			}
			else if(	Math.abs(Double.parseDouble(String.format("%.6f", bls.leA))) ==
						Math.abs(Double.parseDouble(String.format("%.6f", brs.leA))) &&
						Math.abs(Double.parseDouble(String.format("%.6f", bls.leB))) ==
						Math.abs(Double.parseDouble(String.format("%.6f", brs.leB))) &&
						Math.abs(Double.parseDouble(String.format("%.6f", bls.leC))) ==
						Math.abs(Double.parseDouble(String.format("%.6f", brs.leC))))
			{
				intersection_m1m2_x = (intersection_brtr_x + intersection_tlbl_x)/2;
				intersection_m1m2_y = (intersection_brtr_y + intersection_tlbl_y)/2;
			}
			else if(	Math.abs(Double.parseDouble(String.format("%.6f", bls.leA))) ==
						Math.abs(Double.parseDouble(String.format("%.6f", trs.leA))) &&
						Math.abs(Double.parseDouble(String.format("%.6f", bls.leB))) ==
						Math.abs(Double.parseDouble(String.format("%.6f", trs.leB))) &&
						Math.abs(Double.parseDouble(String.format("%.6f", bls.leC))) ==
						Math.abs(Double.parseDouble(String.format("%.6f", trs.leC))))
			{
				intersection_m1m2_x = (intersection_tltr_x + intersection_brbl_x)/2;
				intersection_m1m2_y = (intersection_tltr_y + intersection_brbl_y)/2;
			}
			else if(	Math.abs(Double.parseDouble(String.format("%.6f", tls.leA))) ==
						Math.abs(Double.parseDouble(String.format("%.6f", brs.leA))) &&
						Math.abs(Double.parseDouble(String.format("%.6f", tls.leB))) ==
						Math.abs(Double.parseDouble(String.format("%.6f", brs.leB))) &&
						Math.abs(Double.parseDouble(String.format("%.6f", tls.leC))) ==
						Math.abs(Double.parseDouble(String.format("%.6f", brs.leC))))
			{
				intersection_m1m2_x = (intersection_tltr_x + intersection_brbl_x)/2;
				intersection_m1m2_y = (intersection_tltr_y + intersection_brbl_y)/2;
			}
			else
			{
				tltr_brtr_mid_x = (intersection_tltr_x + intersection_brtr_x)/2;
				tltr_brtr_mid_y = (intersection_tltr_y + intersection_brtr_y)/2;
				tltr_tlbl_mid_x = (intersection_tltr_x + intersection_tlbl_x)/2;
				tltr_tlbl_mid_y = (intersection_tltr_y + intersection_tlbl_y)/2;
				brbl_brtr_mid_x = (intersection_brbl_x + intersection_brtr_x)/2;
				brbl_brtr_mid_y = (intersection_brbl_y + intersection_brtr_y)/2;
				brbl_tlbl_mid_x = (intersection_brbl_x + intersection_tlbl_x)/2;
				brbl_tlbl_mid_y = (intersection_brbl_y + intersection_tlbl_y)/2;
				
				
				//Calculate linear equations of the lines joining midpoints
				m1pa = tltr_brtr_mid_x;
				m1pb = tltr_brtr_mid_y;
				m1pc = brbl_tlbl_mid_x;
				m1pd = brbl_tlbl_mid_y;
				m2pa = tltr_tlbl_mid_x;
				m2pb = tltr_tlbl_mid_y;
				m2pc = brbl_brtr_mid_x;
				m2pd = brbl_brtr_mid_y;
				m1A = m1pd - m1pb;
				m1B = m1pa - m1pc;
				m1C = m1pa * m1pd - m1pc * m1pb;
				m2A = m2pd - m2pb;
				m2B = m2pa - m2pc;
				m2C = m2pa * m2pd - m2pc * m2pb;
				
				//Calculate estimated point
				intersection_m1m2_x = (m1C * m2B - m1B * m2C)/(m1A * m2B - m1B * m2A);
				intersection_m1m2_y = (m1A * m2C - m1C * m2A)/(m1A * m2B - m1B * m2A);
			}
		
		
		Kon.clear();
		
		//Draw antennas
		Kon.drawCircle(trs.getPoint(), 5);
		Kon.drawLine(trs.getX() - 5, 
				trs.getY() + 5 + Math.sqrt(10 * 10 - 5 * 5), 
				trs.getX() + 5, 
				trs.getY() + 5 + Math.sqrt(10 * 10 - 5 * 5));
		Kon.drawLine(trs.getX(), trs.getY() + 5, trs.getX() - 5, trs.getY() + 5 + Math.sqrt(10 * 10 - 5 * 5));
		Kon.drawLine(trs.getX(), trs.getY() + 5, trs.getX() + 5, trs.getY() + 5 + Math.sqrt(10 * 10 - 5 * 5));
		Kon.drawLine(trs.getX() - (5 * 0.4), 
				trs.getY() + 5 + (Math.sqrt(10 * 10 - 5 * 5) * 0.4), 
				trs.getX() + (5 * 0.4), 
				trs.getY() + 5 + (Math.sqrt(10 * 10 - 5 * 5) * 0.4));
		Kon.drawLine(trs.getX() - (5 * 0.7), 
				trs.getY() + 5 + (Math.sqrt(10 * 10 - 5 * 5) * 0.7), 
				trs.getX() + (5 * 0.7), 
				trs.getY() + 5 + (Math.sqrt(10 * 10 - 5 * 5) * 0.7));
		Kon.drawArc(trs.getX() - 5 - 2, trs.getY() - 5 - 2, 14, 14, 50, 80);
		Kon.drawArc(trs.getX() - 5 - 2 - 2, trs.getY() - 5 - 2 - 2, 18, 18, 45, 90);
		
		Kon.drawCircle(tls.getPoint(), 5);
		Kon.drawLine(tls.getX() - 5, 
				tls.getY() + 5 + Math.sqrt(10 * 10 - 5 * 5), 
				tls.getX() + 5, 
				tls.getY() + 5 + Math.sqrt(10 * 10 - 5 * 5));
		Kon.drawLine(tls.getX(), tls.getY() + 5, tls.getX() - 5, tls.getY() + 5 + Math.sqrt(10 * 10 - 5 * 5));
		Kon.drawLine(tls.getX(), tls.getY() + 5, tls.getX() + 5, tls.getY() + 5 + Math.sqrt(10 * 10 - 5 * 5));
		Kon.drawLine(tls.getX() - (5 * 0.4), 
				tls.getY() + 5 + (Math.sqrt(10 * 10 - 5 * 5) * 0.4), 
				tls.getX() + (5 * 0.4), 
				tls.getY() + 5 + (Math.sqrt(10 * 10 - 5 * 5) * 0.4));
		Kon.drawLine(tls.getX() - (5 * 0.7), 
				tls.getY() + 5 + (Math.sqrt(10 * 10 - 5 * 5) * 0.7), 
				tls.getX() + (5 * 0.7), 
				tls.getY() + 5 + (Math.sqrt(10 * 10 - 5 * 5) * 0.7));
		Kon.drawArc(tls.getX() - 5 - 2, tls.getY() - 5 - 2, 14, 14, 50, 80);
		Kon.drawArc(tls.getX() - 5 - 2 - 2, tls.getY() - 5 - 2 - 2, 18, 18, 45, 90);
		
		Kon.drawCircle(bls.getPoint(), 5);
		Kon.drawLine(bls.getX() - 5, 
				bls.getY() + 5 + Math.sqrt(10 * 10 - 5 * 5), 
				bls.getX() + 5, 
				bls.getY() + 5 + Math.sqrt(10 * 10 - 5 * 5));
		Kon.drawLine(bls.getX(), bls.getY() + 5, bls.getX() - 5, bls.getY() + 5 + Math.sqrt(10 * 10 - 5 * 5));
		Kon.drawLine(bls.getX(), bls.getY() + 5, bls.getX() + 5, bls.getY() + 5 + Math.sqrt(10 * 10 - 5 * 5));
		Kon.drawLine(bls.getX() - (5 * 0.4), 
				bls.getY() + 5 + (Math.sqrt(10 * 10 - 5 * 5) * 0.4), 
				bls.getX() + (5 * 0.4), 
				bls.getY() + 5 + (Math.sqrt(10 * 10 - 5 * 5) * 0.4));
		Kon.drawLine(bls.getX() - (5 * 0.7), 
				bls.getY() + 5 + (Math.sqrt(10 * 10 - 5 * 5) * 0.7), 
				bls.getX() + (5 * 0.7), 
				bls.getY() + 5 + (Math.sqrt(10 * 10 - 5 * 5) * 0.7));
		Kon.drawArc(bls.getX() - 5 - 2, bls.getY() - 5 - 2, 14, 14, 50, 80);
		Kon.drawArc(bls.getX() - 5 - 2 - 2, bls.getY() - 5 - 2 - 2, 18, 18, 45, 90);
		
		Kon.drawCircle(brs.getPoint(), 5);
		Kon.drawLine(brs.getX() - 5, 
				brs.getY() + 5 + Math.sqrt(10 * 10 - 5 * 5), 
				brs.getX() + 5, 
				brs.getY() + 5 + Math.sqrt(10 * 10 - 5 * 5));
		Kon.drawLine(brs.getX(), brs.getY() + 5, brs.getX() - 5, brs.getY() + 5 + Math.sqrt(10 * 10 - 5 * 5));
		Kon.drawLine(brs.getX(), brs.getY() + 5, brs.getX() + 5, brs.getY() + 5 + Math.sqrt(10 * 10 - 5 * 5));
		Kon.drawLine(brs.getX() - (5 * 0.4), 
				brs.getY() + 5 + (Math.sqrt(10 * 10 - 5 * 5) * 0.4), 
				brs.getX() + (5 * 0.4), 
				brs.getY() + 5 + (Math.sqrt(10 * 10 - 5 * 5) * 0.4));
		Kon.drawLine(brs.getX() - (5 * 0.7), 
				brs.getY() + 5 + (Math.sqrt(10 * 10 - 5 * 5) * 0.7), 
				brs.getX() + (5 * 0.7), 
				brs.getY() + 5 + (Math.sqrt(10 * 10 - 5 * 5) * 0.7));
		Kon.drawArc(brs.getX() - 5 - 2, brs.getY() - 5 - 2, 14, 14, 50, 80);
		Kon.drawArc(brs.getX() - 5 - 2 - 2, brs.getY() - 5 - 2 - 2, 18, 18, 45, 90);
		
		Kon.setStroke(StrokeAttribute.DOTTED);
		Kon.setColor(ColorName.GRAY);
		
		//Draw line segments from antennas to the phone
		Kon.drawLine(trs.getPoint(), p1.getPoint());
		Kon.drawLine(tls.getPoint(), p1.getPoint());
		Kon.drawLine(bls.getPoint(), p1.getPoint());
		Kon.drawLine(brs.getPoint(), p1.getPoint());
		
		//Draw a phone
		Kon.setStroke(4, StrokeAttribute.SOLID);
		Kon.setColor(ColorName.DODGERBLUE);
		//Kon.drawCircle(p1.getPoint(), 0);
		Kon.drawCircle(p1.getPoint(), 2);
		//Kon.drawCircle(p1.getPoint(), 0);
		
		Kon.setColor(ColorName.BLACK);
		Kon.setStroke(StrokeAttribute.SOLID);
		
		//Draw Bluetooth signal paths
		Kon.drawLine(trs.getPoint(), trs.getHypotEndPoint());
		Kon.drawLine(tls.getPoint(), tls.getHypotEndPoint());
		Kon.drawLine(bls.getPoint(), bls.getHypotEndPoint());
		Kon.drawLine(brs.getPoint(), brs.getHypotEndPoint());
		
		Kon.setStroke(StrokeAttribute.SOLID);
		
		//Draw intersections of the paths
		Kon.drawCircle(intersection_tltr_x, intersection_tltr_y, 3);
		Kon.drawCircle(intersection_tlbl_x, intersection_tlbl_y, 3);
		Kon.drawCircle(intersection_brtr_x, intersection_brtr_y, 3);
		Kon.drawCircle(intersection_brbl_x, intersection_brbl_y, 3);
		
		Kon.setStroke(4, StrokeAttribute.SOLID);
		Kon.setColor(125*(1.0/255), 170*(1.0/255), 19*(1.0/255));
		
		//Draw midpoints
		Kon.drawCircle(tltr_brtr_mid_x, tltr_brtr_mid_y, 1);
		Kon.drawCircle(tltr_tlbl_mid_x, tltr_tlbl_mid_y, 1);
		Kon.drawCircle(brbl_brtr_mid_x, brbl_brtr_mid_y, 1);
		Kon.drawCircle(brbl_tlbl_mid_x, brbl_tlbl_mid_y, 1);
		
		Kon.setStroke(1, StrokeAttribute.DASHED);
		
		//Draw line segments between midpoints
		Kon.drawLine(tltr_brtr_mid_x, tltr_brtr_mid_y, brbl_tlbl_mid_x, brbl_tlbl_mid_y);
		Kon.drawLine(tltr_tlbl_mid_x, tltr_tlbl_mid_y, brbl_brtr_mid_x, brbl_brtr_mid_y);
		
		Kon.setStroke(2, StrokeAttribute.SOLID);
		Kon.setColor(ColorName.RED);
		
		//Draw the estimated point
		Kon.drawCircle(intersection_m1m2_x, intersection_m1m2_y, 1);
		
		
		Kon.refresh();
		
		
		img_aoa_draw = new File("img_aoa_draw.png");
		data_from_Kon = new File("data_from_Kon.txt");
		if(img_aoa_draw.exists()) {
			img_aoa_draw.delete();
			while(true) {
				if(!(img_aoa_draw.exists())) {
					break;
				}
			}
		}
		if(data_from_Kon.exists()) {
			img_aoa_draw.delete();
			while(true) {
				if(!(img_aoa_draw.exists())) {
					break;
				}
			}
		}
		
		Kon.saveGraphics("img_aoa_draw.png");
		
		try {
			FileWriter fileWriter = new FileWriter("data_from_Kon.txt");
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(trs.getRandomNo() + ", " + 
            tls.getRandomNo() + ", " +
            bls.getRandomNo() + ", " +
            brs.getRandomNo() + ", " + 
            String.format("%.4f", intersection_m1m2_x) + ", " + 
            String.format("%.4f", intersection_m1m2_y) + "!");
            //bufferedWriter.newLine();
            bufferedWriter.close();
        }
        catch(IOException ex) {
            System.out.println("Error writing to file.");
        }
		
		class_f1 = new File("Antenna.class");
		class_f2 = new File("Phone.class");
		class_f3 = new File("AoASimulator_Embedded.class");
		if(class_f1.exists()) {
			class_f1.deleteOnExit();
		}
		if(class_f2.exists()) {
			class_f2.deleteOnExit();
		}
		if(class_f3.exists()) {
			class_f3.deleteOnExit();
		}
		while(true) {
			if(img_aoa_draw.exists() && data_from_Kon.exists()) {
				break;
			}
		}
		
		System.exit(0);
	}
}

class Antenna {
	double x, y, px, py, rt_a, rt_a_new2, rt_b_new2, hypot, hypot_new, rt_angle, rt_angle_new, lineScale;
	double nhex, nhey, lepa, lepb, lepc, lepd, leA, leB, leC;
	int ranno;
	String name;

	Antenna(double x_input, double y_input, double px_input, double py_input, String n_input) {
		x = x_input;
		y = y_input;
		px = px_input;
		py = py_input;
		name = n_input;
		ranno = (int)(Math.random() * 11 - 5);
		lineScale = 1.3;
	}
	
	void run_phase1() {
		this.evalHypotHypotNew();
		this.evalRta();
		this.evalRtAngleRtAngleNew();
		this.evalRtaRtbNew2();
		this.evalNewHypotEndPoint();
		this.evalLinearEq();
	}
	
	double getX() {
		return this.x;
	}
	
	double getY() {
		return this.y;
	}
	
	Xy getPoint() {
		return Xy.xy(this.x, this.y);
	}
	
	Xy getHypotEndPoint() {
		return Xy.xy(this.nhex, this.nhey);
	}

	void setPoint(double x_input, double y_input) {
		this.x = x_input;
		this.y = y_input;
	}
	
	int getRandomNo() {
		return this.ranno;
	}
	
	void setRandomNo(int no_input) {
		this.ranno = no_input;
	}
	
	void setLineScale(double ls_input) {
		this.lineScale = ls_input;
	}
	
	void evalHypotHypotNew() {
		this.hypot = Math.hypot(this.x - this.px, this.y - this.py);
		this.hypot_new = this.hypot * this.lineScale;
	}
	
	void evalRta() {
		if(this.name == "tls" || this.name == "brs") {
			this.rt_a = Math.abs(this.x - this.px);
		}
		else if (this.name == "trs" || this.name == "bls") {
			this.rt_a = Math.abs(this.y - this.py);
		}
		else {
			System.out.println("*** Unknown error occurs while rt_a is being calculated!! ***");
		}
	}
	
	void evalRtAngleRtAngleNew() {
		this.rt_angle = Math.toDegrees(Math.asin(this.rt_a/this.hypot));
		this.rt_angle_new = this.rt_angle + this.getRandomNo();
	}
	
	void evalRtaRtbNew2() {
		this.rt_a_new2 = Math.sin(Math.toRadians(this.rt_angle_new)) * this.hypot_new;
		this.rt_b_new2 = Math.sqrt(this.hypot_new * this.hypot_new - this.rt_a_new2 * this.rt_a_new2);
	}
	
	void evalNewHypotEndPoint() {
		if(this.name == "trs") {
			this.nhex = this.x - this.rt_b_new2;
			this.nhey = this.y + this.rt_a_new2;
		}
		else if(this.name == "tls") {
			this.nhex = this.x + this.rt_a_new2;
			this.nhey = this.y + this.rt_b_new2;
		}
		else if(this.name == "bls") {
			this.nhex = this.x + this.rt_b_new2;
			this.nhey = this.y - this.rt_a_new2;
		}
		else if(this.name == "brs") {
			this.nhex = this.x - this.rt_a_new2;
			this.nhey = this.y - this.rt_b_new2;
		}
		else {
			System.out.println("*** Unknown error occurs while hypotenuse end point is being calculated!! ***");
		}
	}
	
	void evalLinearEq() {
		this.lepa = this.x;
		this.lepb = this.y;
	 	this.lepc = this.nhex;
		this.lepd = this.nhey;
		this.leA = this.lepd - this.lepb;
		this.leB = this.lepa - this.lepc;
		this.leC = this.lepa * this.lepd - this.lepc * this.lepb;
	}
	
}

class Phone {
	double x, y;
	
	Phone(double x_input, double y_input) {
		x = x_input;
		y = y_input;
	}
	
	double getX() {
		return this.x;
	}
	
	double getY() {
		return this.y;
	}
	
	Xy getPoint() {
		return Xy.xy(this.x, this.y);
	}
	
	void setPoint(double x_input, double y_input) {
		this.x = x_input;
		this.y = y_input;
	}
}