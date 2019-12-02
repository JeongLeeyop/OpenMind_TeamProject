package mind.controller;

public class ModelAndView {

	private String viewName; // 결과 view의 정보
	private boolean isRedirect; // 이동 방식 정보 (true = redirect / false = forward)

	public ModelAndView() {
	}

	public ModelAndView(String viewName, boolean isRedirect) {
		super();
		this.viewName = viewName;
		this.isRedirect = isRedirect;
	}

	public String getViewName() {
		return viewName;
	}

	public void setViewName(String viewName) {
		this.viewName = viewName;
	}

	public boolean isRedirect() {
		return isRedirect;
	}

	public void setRedirect(boolean isRedirect) {
		this.isRedirect = isRedirect;
	}

}
