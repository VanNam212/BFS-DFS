package danh_gia;

public class Flow implements Comparable<Flow> {

	private String dau;
	private String cuoi;
	private int num;

	public Flow(String dau, String cuoi, int num) {
		super();
		this.dau = dau;
		this.cuoi = cuoi;
		this.num = num;
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

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	@Override
	public int compareTo(Flow o) {
		if (this.getNum() == o.getNum()) {
			return this.getCuoi().compareTo(o.getCuoi());
		} else {
			return this.getNum() - o.getNum();
		}
	}

}
