package a_sao;

public class Flow implements Comparable<Flow> {
	private String dau;
	private String cuoi;
	private int khoang_cach;
	private int num_dau; // trọng số đỉnh đầu
	private int num_cuoi; // trọng số đỉnh cuối

	public Flow(String dau, String cuoi, int khoang_cach, int num_dau, int num_cuoi) {
		this.dau = dau;
		this.cuoi = cuoi;
		this.khoang_cach = khoang_cach;
		this.num_dau = num_dau;
		this.num_cuoi = num_cuoi;
	}

	public Flow(String dau, String cuoi, int num_dau, int num_cuoi) {
		this.dau = dau;
		this.cuoi = cuoi;
		this.num_dau = num_dau;
		this.num_cuoi = num_cuoi;
	}

	public Flow(String dau, int num_dau) {
		super();
		this.dau = dau;
		this.num_dau = num_dau;
	}

	public String getDau() {
		return dau;
	}

	public void setDau(String dau) {
		this.dau = dau;
	}

	public String getCuoi() {
		return cuoi;
	}

	public void setCuoi(String cuoi) {
		this.cuoi = cuoi;
	}

	public int getKhoang_cach() {
		return khoang_cach;
	}

	public void setKhoang_cach(int khoang_cach) {
		this.khoang_cach = khoang_cach;
	}

	public int getNum_dau() {
		return num_dau;
	}

	public void setNum_dau(int num_dau) {
		this.num_dau = num_dau;
	}

	public int getNum_cuoi() {
		return num_cuoi;
	}

	public void setNum_cuoi(int num_cuoi) {
		this.num_cuoi = num_cuoi;
	}

	@Override
	public int compareTo(Flow o) {
		if (this.getNum_cuoi() == o.getNum_cuoi()) {
			return this.getCuoi().compareTo(o.getCuoi());
		} else {
			return this.getNum_cuoi() - o.getNum_cuoi();
		}
	}
}
