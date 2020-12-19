package a_sao;

import a_sao.Flow;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

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
			sc = new Scanner(new File("input7.txt"));
			n = sc.nextInt();
			m = sc.nextInt();
			System.out.println(n + " " + m);
			String dt = sc.nextLine();
			for (int i = 0; i < m; i++) {
				dt = sc.nextLine();
				String[] arr = dt.split(" ");
				list.add(new Flow(arr[0], arr[1], Integer.parseInt(arr[2]), Integer.parseInt(arr[3]),
						Integer.parseInt(arr[4])));
				System.out.println(dt);
			}
		} catch (FileNotFoundException er) {
			// TODO Auto-generated catch block
			er.printStackTrace();
		}

		System.out.println("\n\nBFS - A*");
		BFS(list, s, e);

	}

	public static void BFS(List<Flow> list, String s, String e) {
		List<String> TT = new ArrayList<>();
		List<String> TTK = new ArrayList<>();
		List<Integer> K = new ArrayList<>();
		List<Integer> h = new ArrayList<>();
		List<Integer> g = new ArrayList<>();
		List<Integer> f = new ArrayList<>();
		List<Flow> DSL = new ArrayList<>();
		Queue<String> queue_dinh = new LinkedList<>();
		List<Flow> list_dinh_ke = new ArrayList<>();

		queue_dinh.add(s + " " + 0);
		int buoc = 1;
		int g_min = 0;

		while (queue_dinh.size() > 0) {
			int f_min = 100;
			String str = queue_dinh.poll();
			if (list_dinh_ke.size() > 0) {
				list_dinh_ke.remove(0);
			}
			String[] arr = str.split(" ");
			String c = arr[0];
			int num = Integer.parseInt(arr[1]);
			for (int i = 0; i < DSL.size(); i++) {
				if (DSL.get(i).getCuoi().equals(c) && DSL.get(i).getNum_cuoi() == num) {
					DSL.remove(i);
					break;
				}
			}

			TT.add(c + num);

			System.out.printf("\nBuoc " + buoc + ":\t" + (c + num));
			buoc++;

			if (c.equals(e)) {
				System.out.printf("\tTTKT");
				break;
			}

			String tt = "";

			for (Flow flow : list) {
				if (flow.getDau().equals(c)) {
					tt += flow.getCuoi() + " ";
					int k = flow.getKhoang_cach();
					K.add(k);
					int h_v = flow.getNum_cuoi();
					h.add(h_v);
					int g_v = 0;
					if (c.equals(s)) {
						g_v = k;
					} else {
						g_v = k + g_min;
					}
					g.add(g_v);
					int f_v = h_v + g_v;
					f.add(f_v);

					list_dinh_ke.add(new Flow(flow.getDau(), flow.getCuoi(), flow.getNum_dau(), f_v));
					DSL.add(new Flow(flow.getDau(), flow.getCuoi(), flow.getNum_dau(), f_v));

					System.out.printf("\n" + flow.getCuoi() + "\t" + k + "\t" + h_v + "\t" + g_v + "\t" + f_v + "\t");
				}
			}

			TTK.add(tt);

			for (int i = 0; i < f.size(); i++) {
				if (f_min > f.get(i)) {
					f_min = f.get(i);
					g_min = g.get(i);
				}
			}

			Collections.sort(list_dinh_ke);

			queue_dinh.removeAll(queue_dinh);

			String ds = "";
			for (Flow fl : list_dinh_ke) {
				ds += fl.getCuoi() + fl.getNum_cuoi() + ", ";
				queue_dinh.add(fl.getCuoi() + " " + fl.getNum_cuoi());
			}

			System.out.println(ds);

			g.removeAll(g);
			f.removeAll(f);

		}

		Stack<String> stack = new Stack<>();
		stack.push(e);
		while (!e.equals(s)) {
			for (int i = TTK.size() - 1; i >= 0; i--) {
				int index = TTK.get(i).indexOf(e + "");
				if (index >= 0) {
					e = TT.get(i).charAt(0) + "";
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
