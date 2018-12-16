package com.hyd.rcp.test2;

import java.util.*;

import javax.inject.Inject;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.ui.views.navigator.LocalSelectionTransfer;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.DragDetectEvent;
import org.eclipse.swt.events.DragDetectListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.dnd.DragSource;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DropTarget;
import org.eclipse.swt.dnd.DragSourceAdapter;
import org.eclipse.swt.dnd.DragSourceEvent;
import org.eclipse.swt.dnd.DragSourceListener;
import org.eclipse.swt.dnd.DropTargetAdapter;
import org.eclipse.swt.dnd.DropTargetEvent;
import org.eclipse.swt.dnd.DropTargetListener;
import org.eclipse.swt.dnd.FileTransfer;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;

import com.hyd.ClassSet.Person;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Label;


public class View extends ViewPart {
	public View() {
	}
	public static final String ID = "com.hyd.rcp.test2.view";

	@Inject IWorkbench workbench;
	
	private TableViewer viewer, viewer_1;
	private TableViewerColumn column;
	public ArrayList<Person> p1 = new ArrayList<Person>();
	private Table table_1;
	private Text text;
	private Text text_1;
	
	private class StringLabelProvider extends ColumnLabelProvider {
		@Override
		public String getText(Object element) {
			return super.getText(element);
		}

		@Override
		public Image getImage(Object obj) {
			return workbench.getSharedImages().getImage(ISharedImages.IMG_OBJ_ELEMENT);
		}

	}

	@Override
	public void createPartControl(Composite parent) {
		
		SashForm sashForm_2 = new SashForm(parent, SWT.NONE);
		SashForm sashForm = new SashForm(sashForm_2, SWT.BORDER | SWT.VERTICAL);
		
		// 데이터 초기화
		p1.add(new Person("hwang", "young", "male", true));
		p1.add(new Person("stmhyd", "lgcns", "male", true));
		p1.add(new Person("kim", "kang", "male", true));
		p1.add(new Person("sun", "dougther", "man", true));

		
		Button btn1 = new Button(sashForm, SWT.NONE);
		btn1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Person out = (Person) viewer.getInput();
				System.out.println("result : " + out);
				viewer.refresh();
			}
		});
		btn1.setText("result");
		
		Button btn2 = new Button(sashForm, SWT.NONE);
		btn2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				p1.add(new Person("korea", "mycountry", "male", true));
			}
		});
		btn2.setText("setinput person");
		
		Button btn3 = new Button(sashForm, SWT.NONE);
		btn3.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				viewer.refresh();
			}
		});
		btn3.setText("refresh");
		
		Button btn4 = new Button(sashForm, SWT.NONE);
		btn4.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				colview(viewer);
			}
		});
		btn4.setText("colview");
		
		Button btn5 = new Button(sashForm, SWT.NONE);
		btn5.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				viewer.update(e, null);
				
			}
		});
		btn5.setText("update");
		
		text = new Text(sashForm, SWT.BORDER);
		
		DragSource dragSource_1 = new DragSource(text, DND.DROP_MOVE);
		
		text_1 = new Text(sashForm, SWT.BORDER);
		
		text_1.addDragDetectListener(new DragDetectListener() {
			public void dragDetected(DragDetectEvent e) {
				text_1.setText("drag event");
			}
		});
		DropTarget dropTarget_1 = new DropTarget(text_1, DND.DROP_MOVE);
		
		Label lblNewLabel = new Label(sashForm, SWT.NONE);
		lblNewLabel.setText("Drag&Drop test");
		
		
		/////////////////////////////////		
		int operations = DND.DROP_MOVE | DND.DROP_COPY;
//		int operations = DND.DROP_COPY;
		DragSource source = new DragSource(lblNewLabel, operations);

		// Provide data in Text format
		Transfer[] types = new Transfer[] {TextTransfer.getInstance()};
		source.setTransfer(types);
		
		Label lblNewLabel_1 = new Label(sashForm, SWT.NONE);
		lblNewLabel_1.setText("New Label");
		
		DropTarget dropTarget_2 = new DropTarget(lblNewLabel_1, operations);
		Transfer[] formats1 = new Transfer[] { TextTransfer.getInstance()};
		dropTarget_2.setTransfer(formats1);
		
		dropTarget_2.addDropListener(new DropTargetListener() {
			@Override
			public void dragEnter(DropTargetEvent event) {
				if (TextTransfer.getInstance().isSupportedType(event.currentDataType)){
				}
			}
			@Override
			public void drop(DropTargetEvent event) {
				if (TextTransfer.getInstance().isSupportedType(event.currentDataType)){
					String files = (String)event.data;
					lblNewLabel_1.setText(files);
					
/*					for (int i = 0; i < files.length; i++) {
						lblNewLabel_1.setText(files);
//						text_filename.setText(files[i]);
					}*/
					System.out.println("drop event");
				}
			}
			@Override
			public void dragLeave(DropTargetEvent event) {
				// TODO Auto-generated method stub
				System.out.println("dropleave event");
				
			}
			@Override
			public void dragOperationChanged(DropTargetEvent event) {
				// TODO Auto-generated method stub
				System.out.println("drop");
				
			}
			@Override
			public void dragOver(DropTargetEvent event) {
				// TODO Auto-generated method stub
				System.out.println("drop");
				
			}
			@Override
			public void dropAccept(DropTargetEvent event) {
				// TODO Auto-generated method stub
				event.getSource();
				
			}
		});

		source.addDragListener(new DragSourceListener() {
			public void dragStart(DragSourceEvent event) {
				// Only start the drag if there is actually text in the
				// label - this text will be what is dropped on the target.
				if (lblNewLabel.getText().length() == 0) {
					event.doit = false;
				}
			}
			public void dragSetData(DragSourceEvent event) {
				// Provide the data of the requested type.
				if (TextTransfer.getInstance().isSupportedType(event.dataType)) {
					event.data = lblNewLabel.getText();
				}
			}
			public void dragFinished(DragSourceEvent event) {
				// If a move operation has been performed, remove the data
				// from the source
				if (event.detail == DND.DROP_MOVE)
					lblNewLabel.setText("");
			}
		});
		///////////////////////////
		
		dropTarget_1.addDropListener(new DropTargetAdapter() {
			@Override
			public void dragEnter(DropTargetEvent event) {
			}
		});
		dragSource_1.addDragListener(new DragSourceAdapter() {
			@Override
			public void dragStart(DragSourceEvent event) {
			}
		});
		
		sashForm.setWeights(new int[] {237, 221, 229, 229, 229, 229, 229, 229, 229});
		
				
		SashForm sashForm_1 = new SashForm(sashForm_2, SWT.BORDER);
		viewer = new TableViewer(sashForm_1, SWT.FULL_SELECTION | SWT.MULTI);
		Table table = viewer.getTable();
		table.setHeaderVisible(true);
		viewer.getTable().setLinesVisible(true);

//		column = new TableViewerColumn(viewer, SWT.NONE);
		
//		String[] colname = new String[] {"View"};
//		viewer.setColumnProperties(colname);
		
//		CellEditor[] celledit = new CellEditor[1]; 
//		TextCellEditor textCellEditor = new TextCellEditor(table);
//		celledit[0]	= textCellEditor;
//		viewer.setCellEditors(celledit);

		
//		viewer.getTable().getColumn(0).setWidth(200);
		viewer.setContentProvider(ArrayContentProvider.getInstance());
		viewer.setInput(p1);
		
		/* 
		 * 컬럼 설정
		 */
//		column.setLabelProvider(new StringLabelProvider());
		colview(viewer);

		// Provide the input to the ContentProvider
//		viewer.setInput(createInitialDataModel());

		/*
		 *  edit setting
		 */
		TableColumn tableColumn = column.getColumn();
//		tableColumn.setText("hyd");
		
		column.setEditingSupport(new EditingSupport(viewer) {
			protected boolean canEdit(Object element) {
				// TODO Auto-generated method stub
				System.out.println("canEdit");
				
				return true;
			}
			protected CellEditor getCellEditor(Object element) {
				// TODO Auto-generated method stub
				System.out.println("getcelleditor");
				TextCellEditor textCellEditor1 = new TextCellEditor(table);
				return textCellEditor1;
//				return null;
			}
			protected Object getValue(Object element) {
				// TODO Auto-generated method stub
				Person input = (Person) element;
				System.out.println("getvalue");
				System.out.println(input);
				return input.getGender();
//				return null;
			}
			protected void setValue(Object element, Object value) {
				System.out.println("setvalue");
				System.out.println(element + " " + value);
				// TODO Auto-generated method stub
				Person input = (Person) element;
				input.setGender(String.valueOf(value));
				
				viewer.refresh();
				viewer.update(element, null);
				
			}
		});
		
		DragSource dragSource = new DragSource(table, DND.DROP_MOVE | DND.DROP_COPY | DND.DROP_DEFAULT);
		dragSource.addDragListener(new DragSourceAdapter() {
			@Override
			public void dragStart(DragSourceEvent event) {
			}
			@Override
			public void dragSetData(DragSourceEvent event) {
			}
			@Override
			public void dragFinished(DragSourceEvent event) {
			}
		});
		
		viewer_1 = new TableViewer(sashForm_1, SWT.BORDER | SWT.FULL_SELECTION);
		table_1 = viewer_1.getTable();
		table_1.setLinesVisible(true);
		table_1.setHeaderVisible(true);
		
		TableViewerColumn tableViewerColumn = new TableViewerColumn(viewer_1, SWT.NONE);
		TableColumn tblclmnNewColumn = tableViewerColumn.getColumn();
		tblclmnNewColumn.setWidth(100);
		tblclmnNewColumn.setText("New Column");
		
		int operations1 = DND.DROP_COPY| DND.DROP_MOVE;
		Transfer[] transferTypes = { LocalSelectionTransfer.getTransfer() };
		dragSource.setTransfer(transferTypes);
//		viewer.addDragSupport(operations, transferTypes , new MyDragListener(viewer));

		
		DropTarget dropTarget = new DropTarget(table_1, DND.DROP_MOVE);
		
		Transfer[] formats = { LocalSelectionTransfer.getTransfer() };;
		dropTarget.setTransfer(formats);
		
		dropTarget.addDropListener(new DropTargetAdapter() {
			@Override
			public void dragEnter(DropTargetEvent event) {
			}
			@Override
			public void drop(DropTargetEvent event) {
			}
			@Override
			public void dropAccept(DropTargetEvent event) {
			}
			@Override
			public void dragOver(DropTargetEvent event) {
				event.getSource();
			}
		});
		
		sashForm_1.setWeights(new int[] {1, 1});
		sashForm_2.setWeights(new int[] {111, 480});
		viewer.refresh();
		
		
	}


	@Override
	public void setFocus() {
		viewer.getControl().setFocus();
	}
	
	private List<String> createInitialDataModel() {
		return Arrays.asList("One", "Two", "Three", "Four");
	}
	
	private Person NewData() {

		return new Person("kim", "lee", "women", true);
	}
	
	public void colview(TableViewer viewer) {
		String[] titles = { "firstname", "lastname", "gender"};
		int[] bounds = {120,150,120}; 
		
//		Person p1 = new Person("hyd", "stm", "man", true);
		System.out.println("colview : " + viewer.getInput());

//		TableViewerColumn col; 
//		viewer.refresh();
		// 1st column is for msg grp id
		column = createTableViewerColumn(viewer, titles[0], bounds[0], 0);
		column.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				Person p = (Person) element;
				System.out.println("person : " + p.getFirstName());
				return p.getFirstName();
			}
		});
		// 2st column is for msg grp id
		column = createTableViewerColumn(viewer, titles[1], bounds[1], 0);
		column.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				Person p = (Person) element;
				System.out.println("person : " + p.getLastName());
				return p.getLastName();
			}
		});
		// 3st column is for msg grp id
		column = createTableViewerColumn(viewer, titles[2], bounds[2], 0);
		column.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				Person p = (Person) element;
				System.out.println("person : " + p.getGender());
				return p.getGender();
			}
		});
	}
	
	public void colview1(TableViewer viewer) {
		String[] titles = { "firstname", "lastname", "gender"};
		int[] bounds = {120,150,120}; 
		
//		Person p1 = new Person("hyd", "stm", "man", true);
		System.out.println("colview : " + viewer.getInput());

//		TableViewerColumn col; 
//		viewer.refresh();
		// 1st column is for msg grp id
		column = createTableViewerColumn(viewer, titles[0], bounds[0], 0);
		column.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				Person p = (Person) element;
				System.out.println("person : " + p.getFirstName());
				return p.getFirstName();
			}
		});
		// 2st column is for msg grp id
		column = createTableViewerColumn(viewer, titles[1], bounds[1], 0);
		column.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				Person p = (Person) element;
				System.out.println("person : " + p.getLastName());
				return p.getLastName();
			}
		});
		// 3st column is for msg grp id
		column = createTableViewerColumn(viewer, titles[2], bounds[2], 0);
		column.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				Person p = (Person) element;
				System.out.println("person : " + p.getGender());
				return p.getGender();
			}
		});
	}
	
    private TableViewerColumn createTableViewerColumn(TableViewer tableViewer, String title, int bound, final int colNumber) {
        final TableViewerColumn viewerColumn = new TableViewerColumn(tableViewer, SWT.NONE);
        final TableColumn column = viewerColumn.getColumn();
//      column.setAlignment(SWT.CENTER);
        column.setText(title);
        column.setWidth(bound);
        column.setResizable(true);
        column.setMoveable(true);
//      column.addSelectionListener(getSelectionAdapter(column, colNumber));
        return viewerColumn;
    }

	
	
}