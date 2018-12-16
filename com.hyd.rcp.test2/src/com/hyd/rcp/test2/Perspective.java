package com.hyd.rcp.test2;

import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

public class Perspective implements IPerspectiveFactory {

	@Override
	public void createInitialLayout(IPageLayout layout) {
		addPerspectiveShortcuts(layout);
		layout.setEditorAreaVisible(false);
		layout.setFixed(true);
		
	}

	private void addPerspectiveShortcuts(IPageLayout layout) {
		layout.addPerspectiveShortcut("com.hyd.rcp.test2.perspective");
	}
}
