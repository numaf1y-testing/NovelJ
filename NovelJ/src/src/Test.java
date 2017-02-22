package src;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.*;

import org.lwjgl.glfw.Callbacks;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;

public class Test {
	
	private int Width = 300;
	private int Height = 300;
	
	private long window;

	public static void main(String[] args) {
		new Test().run();
	}
	
	public void run(){
		try{
			start();
			render();
			glfwDestroyWindow(window);
		}finally{
			Callbacks.glfwFreeCallbacks(window);
			glfwTerminate();
		}
	}
	
	private void start(){
		if(!glfwInit()){
			throw new IllegalStateException("Not enable GLFW Init");
		}
		
		glfwDefaultWindowHints();
		glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
		glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);
		
		window = glfwCreateWindow(Width, Height, "Light! Weight! Test!", NULL, NULL);
		if(window == NULL){
			throw new RuntimeException("ウィンドウの生成に失敗しました");
		}
		
		glfwSetWindowAspectRatio(window,1,1);
		
		GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());
		glfwSetWindowPos(window, (vidmode.width()-Width)/ 2, (vidmode.height()-Height)/ 2);
		glfwMakeContextCurrent(window);
		glfwSwapInterval(1);
		glfwShowWindow(window);
		
	}
	
	private void render(){
		GL.createCapabilities();
		
		gl_init();
		
		while(!glfwWindowShouldClose(window)){
			glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
			
			display();
			
			glfwSwapBuffers(window);
			glfwPollEvents();
		}
	}
	
	private void gl_init(){
		glfwSetWindowSizeCallback(window,(window,width,height) -> {
			glViewport(0,0,width,height);
		});
		glClearColor(0.3f,0.3f,0.5f,0.0f);
	}
	
	private void display(){
		glBegin(GL_TRIANGLES);
		glVertex3f(-0.6f,0.2f,0.5f);
		glVertex3f(0.6f,-0.4f,-0.5f);
		glVertex3f(0.8f,0.6f,0.0f);
		glEnd();
	}
}