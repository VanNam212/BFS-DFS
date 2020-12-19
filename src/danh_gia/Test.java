package danh_gia;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class Test {

	private static Scanner sc;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int n, m; // n dinh, m canh
		String s, e; // dinh dau s, dinh cuoi e
		s = "B";
		e = "L";
		List<Flow> list = new ArrayList<>();

		try {
			sc = new Scanner(new File("input6.txt"));
			n = sc.nextInt();
			m = sc.nextInt();
			System.out.println(n + " " + m);
			String dt = sc.nextLine();
			for (int i = 0; i < m; i++) {
				dt = sc.nextLine();
				String[] arr = dt.split(" ");
				list.add(new Flow(arr[0], arr[1], Integer.parseInt(arr[2])));
				System.out.println(dt);
			}
		} catch (FileNotFoundException er) {
			// TODO Auto-generated catch block
			er.printStackTrace();
		}

		System.out.println("\n\nBFS - Tốt nhất đi trước");
		BFS(list, s, e);

		System.out.println("\n\nDFS - Leo đồi");
		DFS(list, s, e);

	}

	public static void BFS(List<Flow> list, String s, String e) {
		List<String> phat_trien_tt = new ArrayList<>();
		List<String> trang_thai = new ArrayList<>();
		Queue<String> queue = new LinkedList<>();
		List<Flow> list_ds = new ArrayList<Flow>();

		queue.add(s);
		int buoc = 1;

		while (queue.size() > 0) {
			String c = queue.poll();
			for (int i = 0; i < list_ds.size(); i++) {
				if (list_ds.get(i).getCuoi().equals(c)) {
					list_ds.remove(i);
					break;
				}
			}

			// xét các đỉnh đã duyệt
			if (phat_trien_tt.contains(c)) {
				continue;
			} else {
				phat_trien_tt.add(c);
			}

			// không xét các đỉnh đã duyệt
//			phat_trien_tt.add(c);

			System.out.printf("\nBuoc " + buoc + "\t" + c + ":\t");
			buoc++;

			if (c.equals(e)) {
				System.out.printf("TTKT");
				break;
			}

			List<Flow> list_dinh_ke = new ArrayList<>();
			String tt = "";
			for (Flow flow : list) {
				if (flow.getDau().equals(c)) {
					tt += flow.getCuoi();
					list_dinh_ke.add(flow);
					System.out.printf(flow.getCuoi() + " ");
				}
			}

			for (Flow flow : list_ds) {
				list_dinh_ke.add(flow);
			}

			Collections.sort(list_dinh_ke);

			trang_thai.add(tt);
			tt = "";

			System.out.printf("\t");
			
			queue.removeAll(queue);
			list_ds.removeAll(list_ds);

			for (Flow flow : list_dinh_ke) {
				queue.add(flow.getCuoi());
				list_ds.add(flow);
			}
			
			Object[] arr = queue.toArray();
			for (Object str : arr) {
				System.out.printf(str + " ");
			}

		}

		Stack<String> stack = new Stack<>();
		stack.push(e);
		while (!e.equals(s)) {
			for (int i = trang_thai.size() - 1; i >= 0; i--) {
				int index = trang_thai.get(i).indexOf(e + "");
				if (index >= 0) {
					e = phat_trien_tt.get(i);
					stack.push(e);
				}
			}
		}
		System.out.println("\n\nĐường đi: ");
		while (stack.size() > 0) {
			System.out.printf(stack.pop() + "->");
		}
	}

	public static void DFS(List<Flow> list, String s, String e) {
		List<String> phat_trien_tt = new ArrayList<>();
		List<String> trang_thai = new ArrayList<>();
		Queue<String> queue = new LinkedList<>();
		List<Flow> list_ds = new ArrayList<Flow>();

		queue.add(s);
		int buoc = 1;

		while (queue.size() > 0) {
			String c = queue.poll();
			for (int i = 0; i < list_ds.size(); i++) {
				if (list_ds.get(i).getCuoi().equals(c)) {
					list_ds.remove(i);
					break;
				}
			}

			// xét các đỉnh đã duyệt
			if (phat_trien_tt.contains(c)) {
				continue;
			} else {
				phat_trien_tt.add(c);
			}

			// không xét các đỉnh đã duyệt
//			phat_trien_tt.add(c);

			System.out.printf("\nBuoc " + buoc + "\t" + c + ":\t");
			buoc++;

			if (c.equals(e)) {
				System.out.printf("TTKT");
				break;
			}

			List<Flow> list_dinh_ke = new ArrayList<>();
			String tt = "";
			for (Flow flow : list) {
				if (flow.getDau().equals(c)) {
					tt += flow.getCuoi();
					list_dinh_ke.add(flow);
					System.out.printf(flow.getCuoi() + " ");
				}
			}

			Collections.sort(list_dinh_ke);
			
			for (Flow flow : list_ds) {
				list_dinh_ke.add(flow);
			}

			trang_thai.add(tt);
			tt = "";

			System.out.printf("\t");
			
			queue.removeAll(queue);
			list_ds.removeAll(list_ds);

			for (Flow flow : list_dinh_ke) {
				queue.add(flow.getCuoi());
				list_ds.add(flow);
			}
			
			Object[] arr = queue.toArray();
			for (Object str : arr) {
				System.out.printf(str + " ");
			}

		}
		
		Stack<String> stack = new Stack<>();
		stack.removeAll(stack);
		stack.push(e);
		while (e != s) {
			for (int i = trang_thai.size() - 1; i >= 0; i--) {
				int index = trang_thai.get(i).indexOf(e + "");
				if (index >= 0) {
					e = phat_trien_tt.get(i);
					stack.push(e);
				}
			}
		}
		System.out.println("\n\nĐường đi: ");
		while (stack.size() > 0) {
			System.out.printf(stack.pop() + "->");
		}
	}

}
