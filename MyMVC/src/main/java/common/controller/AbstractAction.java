package common.controller;

public abstract class AbstractAction implements Action {

	private String viewName; // 보여줄 뷰 페이지(jsp)
	private boolean isRedirect = false; // true => redirect방식으로, false => forward 방식으로
	// execute()추상메서드를 가지고 있음

	// setter, getter
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
