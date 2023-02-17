package com.qa.model;

public class QaVO implements java.io.Serializable {
	private Integer qa_no;
	private Integer qa_show_no;
	private String question;
	private String answer;
	private Byte qa_state;
	private Integer qa_class_id;
	
	public Integer getQa_class_id() {
		return qa_class_id;
	}
	public void setQa_class_id(Integer qa_class_id) {
		this.qa_class_id = qa_class_id;
	}
	public Integer getQa_no() {
		return qa_no;
	}
	public void setQa_no(Integer qa_no) {
		this.qa_no = qa_no;
	}
	public Integer getQa_show_no() {
		return qa_show_no;
	}
	public void setQa_show_no(Integer qa_show_no) {
		this.qa_show_no = qa_show_no;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public Byte getQa_state() {
		return qa_state;
	}
	public void setQa_state(Byte qa_state) {
		this.qa_state = qa_state;
	}
	
	public com.qaclass.model.QaclassVO getQaclass(){
		com.qaclass.model.QaclassService qaclassSvc= new com.qaclass.model.QaclassService();
		com.qaclass.model.QaclassVO qaclass =qaclassSvc.getOneQaclassVO(qa_class_id);
		return qaclass;

			}
	

}
