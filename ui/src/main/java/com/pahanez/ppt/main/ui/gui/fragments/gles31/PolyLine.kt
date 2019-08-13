package com.pahanez.ppt.main.ui.gui.fragments.gles31

import android.opengl.GLES20
import android.opengl.GLES31.*
import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.nio.FloatBuffer

class PolyLine(
    val vertices: FloatArray = floatArrayOf(
        1.0f, 0.0f,
        1.0f, 1.0f,
        0.0f, 0.0f,
        0.5f, 0.3f,
        0.0f, 0.0f,
        0.6f, 0.6f
    )
) {

    private val vertexShaderCode = // This matrix member variable provides a hook to manipulate
        // the coordinates of the objects that use this vertex shader
        "uniform mat4 uMVPMatrix;" +
                "attribute vec4 a_Position;" +
                "void main() {" +
                // The matrix must be included as a modifier of gl_Position.
                // Note that the uMVPMatrix factor *must be first* in order
                // for the matrix multiplication product to be correct.
                "  gl_Position = uMVPMatrix * a_Position;" +
                "}"

    private val fragmentShaderCode = "precision mediump float;" +
            "uniform vec4 u_Color;" +
            "void main() {" +
            "  gl_FragColor = u_Color;" +
            "}"

    internal val COORDS_PER_VERTEX = 2
    private val vertexBuffer: FloatBuffer
    private var shaderProgram: Int
    private var u_colorLocation: Int = 0
    private var a_positionLocation: Int = 0
    private var u_mvpMatrixLocation: Int = 0

    init {

        val bb = ByteBuffer.allocateDirect(
            // (# of coordinate values * 4 bytes per float)
            vertices.size * 4
        )
        bb.order(ByteOrder.nativeOrder())
        vertexBuffer = bb.asFloatBuffer()
        vertexBuffer.put(vertices)
        vertexBuffer.position(0)

        // prepare shaders and OpenGL program
        val vertexShader = MyGLRenderer.loadShader(
            GL_VERTEX_SHADER,
            vertexShaderCode
        )
        val fragmentShader = MyGLRenderer.loadShader(
            GL_FRAGMENT_SHADER,
            fragmentShaderCode
        )

        shaderProgram = glCreateProgram()             // create empty OpenGL Program
        glAttachShader(shaderProgram, vertexShader)   // add the vertex shader to program
        glAttachShader(shaderProgram, fragmentShader) // add the fragment shader to program
        glLinkProgram(shaderProgram)

        bindData()
    }

    private fun bindData() {
        GLES20.glUseProgram(shaderProgram)
        u_colorLocation = glGetUniformLocation(shaderProgram, "u_Color")
        glUniform4f(u_colorLocation, 0.0f, 1.0f, 0.0f, 1.0f)
        a_positionLocation = glGetAttribLocation(shaderProgram, "a_Position")
        vertexBuffer.position(0)
        glVertexAttribPointer(a_positionLocation, 2, GL_FLOAT, true, 0, vertexBuffer)
        glEnableVertexAttribArray(a_positionLocation)
    }

    fun glDraw(mMVPMatrix: FloatArray) {
        glUseProgram(shaderProgram)
        u_mvpMatrixLocation = glGetUniformLocation(shaderProgram, "uMVPMatrix")

        // Apply the projection and view transformation
        glUniformMatrix4fv(u_mvpMatrixLocation, 1, false, mMVPMatrix, 0)
        glDrawArrays(GLES20.GL_LINES, 0, vertices.size / 2)
    }

}