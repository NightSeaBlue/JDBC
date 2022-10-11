package c_info2;

public class InfoVO {
	
	private String name;
	private String id;
	private String tel;
	private String sex;
	private int age;
	private String home;
	
	//toString 
	@Override
	public String toString() {
		return "InfoVO [name=" + name + ", id=" + id + ", tel=" + tel + ", sex=" + sex + ", age=" + age + ", home="
				+ home + "]\n";
	}

	//Constructor (기본)
	public InfoVO() {}
	
	//Constructor (초기화)
	public InfoVO(String name, String id, String tel, String sex, int age, String home) {
		super();
		this.name = name;
		this.id = id;
		this.tel = tel;
		this.sex = sex;
		this.age = age;
		this.home = home;
	}
	
	// Setter, Getter
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getHome() {
		return home;
	}
	public void setHome(String home) {
		this.home = home;
	}
	
}
