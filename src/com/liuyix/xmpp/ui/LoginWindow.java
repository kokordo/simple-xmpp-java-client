package com.liuyix.xmpp.ui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;

public class LoginWindow {

	private String user;
	private String passwd;
	private String server;
	protected Shell shlJclient;
	private Text userTxt;
	private Text pwdTxt;
	private Text servTxt;
	private LoginListener loginListener;
	private boolean enableDebug;
	private static ResourceManager resourceManager = null;
	private Button debugBtn;

	public LoginWindow(boolean debug,String user,String passwd,String host) {
		super();
		// TODO Auto-generated constructor stub
		this.enableDebug = debug;
		this.user = user;
		this.passwd = passwd;
		this.server = host;
		if(resourceManager == null){
			resourceManager = ResourceManager.getInstance();
		}
		
	}	
	
	public LoginWindow() {
		this(false,"","","");
	}

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			LoginWindow window = new LoginWindow();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shlJclient.open();
		shlJclient.layout();
		if(enableDebug){
			userTxt.setText(user);
			pwdTxt.setText(passwd);
			servTxt.setText(server);
		}
		
		while (!shlJclient.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shlJclient = new Shell(SWT.SYSTEM_MODAL |SWT.SHELL_TRIM);
		shlJclient.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
		shlJclient.setSize(205, 432);
		shlJclient.setText("JClient");
		shlJclient.setImage(resourceManager.getImage(ResourceManager.logo));
		shlJclient.setLayout(new FillLayout(SWT.VERTICAL));
		
		Image logo = resourceManager.getImage(ResourceManager.login_image);
//		System.out.println("width=" + logo.getImageData().width);
//		System.out.println("Height:" + logo.getImageData().height);
		//动态设置顶层shell的大小————根据图片的宽度
		shlJclient.setSize(logo.getImageData().width,logo.getImageData().height * 2);
		Label picLable = new Label(shlJclient, SWT.NONE);
		picLable.setToolTipText("查看图片");
		picLable.setBackground(SWTResourceManager.getColor(SWT.COLOR_CYAN));
		picLable.setImage(logo);
		Composite loginInfo = new Composite(shlJclient, SWT.NONE);
		
		GridLayout gl_loginInfo = new GridLayout(3, true);
		loginInfo.setLayout(gl_loginInfo);
		
		Label userLbl = new Label(loginInfo, SWT.NONE);
		userLbl.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		userLbl.setText("用户名：");
		
		new Label(loginInfo,SWT.NONE);
		new Label(loginInfo, SWT.NONE);
		
		userTxt = new Text(loginInfo, SWT.BORDER);
		userTxt.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 3, 1));
		
		Label pwdLbl = new Label(loginInfo, SWT.CENTER);
		pwdLbl.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false, false, 1, 1));
		pwdLbl.setText("密码:");
		
		new Label(loginInfo,SWT.NONE);
		new Label(loginInfo, SWT.NONE);
		
		pwdTxt = new Text(loginInfo, SWT.BORDER | SWT.PASSWORD);
		pwdTxt.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 3, 1));
		
		Label servLabel = new Label(loginInfo, SWT.NONE);
		servLabel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		servLabel.setText("服务器：");
		
		new Label(loginInfo,SWT.NONE);
		new Label(loginInfo, SWT.NONE);
		
		servTxt = new Text(loginInfo, SWT.BORDER);
		servTxt.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 3, 1));
		
		debugBtn = new Button(loginInfo,SWT.CHECK);
		debugBtn.setText("debug");
		debugBtn.setSelection(enableDebug);
		debugBtn.setLayoutData(new GridData(SWT.FILL,SWT.CENTER,false,true,1,1));
		debugBtn.addSelectionListener(new SelectionAdapter() {

			/* (non-Javadoc)
			 * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
			 */
			@Override
			public void widgetSelected(SelectionEvent e) {
								
			}
			
		});
		
		Button loginButton = new Button(loginInfo, SWT.NONE);
		loginButton.setLayoutData(new GridData(SWT.FILL,SWT.CENTER,false,true,1,1));
		loginButton.setText("登录");
		new Label(loginInfo, SWT.NONE);
		loginButton.addSelectionListener(new LoginBtnAdapter());
		
//		Button registerBtn = new Button(loginInfo,SWT.PUSH);
//		registerBtn.setText("注册新帐号");
//		registerBtn.addSelectionListener(new SelectionAdapter() {
//
//			/* (non-Javadoc)
//			 * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
//			 */
//			@Override
//			public void widgetSelected(SelectionEvent e) {
//				// TODO 注册功能
//				
//			}
//			
//		});
		
		
//		new Label(loginInfo, SWT.NONE);
//		new Label(loginInfo, SWT.NONE);
//		new Label(loginInfo, SWT.NONE);
//		new Label(loginInfo, SWT.NONE);
//		new Label(loginInfo, SWT.NONE);
//		new Label(loginInfo, SWT.NONE);
//		new Label(loginInfo, SWT.NONE);
		
//		shlJclient.setSize(logo.getImageData().width,loginInfo.getBounds().height + logo.getImageData().height);
	}
	/**
	 * 登录操作
	 * */
	private class LoginBtnAdapter extends SelectionAdapter {

		@Override
		public void widgetSelected(SelectionEvent e) {
			// 
//			super.widgetSelected(e);
			String username = userTxt.getText();
			String passwd = pwdTxt.getText();
			String serverName = servTxt.getText();
			boolean debug = debugBtn.getSelection();
//			System.out.println("\nusername:" + username + "\npasswd:" + passwd + "\nserverName:" + serverName);
			loginListener.handleLoginInfo(username, passwd, serverName,debug);		
		}
		
	}
	public void addLoginListener(LoginListener listener) {
		this.loginListener  = listener;		
	}
	
	//close this shell
	public void close() {
		//
//		com.liuyix.xmpp.Util.showDebugMsg("get here");
		shlJclient.dispose();
		
	}
	
	
	
}
