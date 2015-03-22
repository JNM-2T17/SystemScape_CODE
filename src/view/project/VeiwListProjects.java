//package view.supplier;
//
//import java.awt.event.ActionEvent;
//import java.util.Iterator;
//
//import javax.swing.JFrame;
//
//import controller.SupplierController;
//import model.Supplier;
//import view.Observer;
//import view.ViewSpecificTemplate;
//import view.purchaseOrder.ViewSpecificPO;
//
//public class VeiwListProjects extends ViewSpecificTemplate{
//	
//	private ViewSpecificProject viewSpecific;
//	private JFrame frame;
//	private String proj;
//	private TabProject tab;
//	
//	private String objNext;
//	private String objPrev;
//	private Iterator it;
//	public VeiwListProjects(TabProject tab, JFrame frame, Iterator it, String proj)
//	{
//		super();
//		
//		this.it=it;
//		objNext=objPrev=null;
//		this.tab=tab;
//		this.proj=proj;
//		
//		viewSpecific = new ViewSpecificProject();
//		viewSpecific.setProject(proj);
//		setContentPanel(viewSpecific);
//		
//		this.getCloseButton().addActionListener(this);
//		this.getEditButton().addActionListener(this);
//		this.getPrevButton().addActionListener(this);
//		this.getNextButton().addActionListener(this);
//		
//		setButtonObjects();
//	}
//
//	@Override
//	public void actionPerformed(ActionEvent e) {
//		// TODO Auto-generated method stub
//		if(e.getSource()==getCloseButton()) tab.setReturn();
//		else if(e.getSource()==getEditButton()) tab.setEdit(proj);
//		else if(e.getSource()==getPrevButton()) tab.setView(objPrev);
//		else if(e.getSource()==getNextButton()) tab.setView(objNext);
//	}
//
//	@Override
//	public void setButtonObjects() {
//		// TODO Auto-generated method stub
//		String first=null;
//		boolean found=false;
//		while(it.hasNext()&&!found){
//			String obj=(String) it.next();
//			if(objPrev==null) first=obj;
//			if(obj.getName().equals(proj)){
//				found=true;
//				if(objPrev==null){
//					if(it.hasNext()){
//						objNext=objPrev=(String) it.next();
//						while(it.hasNext()){
//							objPrev=(String) it.next();
//						}
//					}
//					else{
//						objNext=objPrev=obj;
//					}
//				}
//				else{
//					if(it.hasNext()){
//						objNext=(String) it.next();
//					}
//					else{
//						objNext=first;
//					}
//				}
//			}
//			else{
//				objPrev=obj;
//			}
//				
////			i++;
//		}
//	}
//
//}
