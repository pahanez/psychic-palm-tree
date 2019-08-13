package com.pahanez.ppt.main.ui.gui.fragments.gles31

import android.opengl.GLES31
import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.nio.FloatBuffer
import java.nio.ShortBuffer

class BackArea {
    private val vertexShaderCode = // This matrix member variable provides a hook to manipulate
        // the coordinates of the objects that use this vertex shader
        "uniform mat4 uMVPMatrix;" +
                "attribute vec4 vPosition;" +
                "void main() {" +
                // The matrix must be included as a modifier of gl_Position.
                // Note that the uMVPMatrix factor *must be first* in order
                // for the matrix multiplication product to be correct.
                "  gl_Position = uMVPMatrix * vPosition;" +
                "}"

    private val fragmentShaderCode = "precision mediump float;" +
            "uniform vec4 vColor;" +
            "void main() {" +
            "  gl_FragColor = vColor;" +
            "}"

    private val vertexBuffer: FloatBuffer
    private val drawListBuffer: ShortBuffer
    private val mProgram: Int
    private var mPositionHandle: Int = 0
    private var mColorHandle: Int = 0
    private var mMVPMatrixHandle: Int = 0

    // number of coordinates per vertex in this array
    internal val COORDS_PER_VERTEX = 3
    /*internal var squareCoords = floatArrayOf(
        -0.5f, 0.5f, 0.0f, // top left
        -0.5f, -0.5f, 0.0f, // bottom left
        0.5f, -0.5f, 0.0f, // bottom right
        0.5f, 0.5f, 0.0f // top right
    )*/
    internal var squareCoords = floatArrayOf(
        0.0f, 1.0f, .0f, // top left
        0.0f, 0.0f, .0f, // bottom left
        1.0f, 0.0f, .0f, // bottom right
        1.0f, 1.0f, .0f // top right
    )


    private val drawOrder = shortArrayOf(0, 1, 2, 0, 2, 3) // order to draw vertices

    private val vertexStride = COORDS_PER_VERTEX * 4 // 4 bytes per vertex

    internal var color = floatArrayOf(1.0f, 1.0f, 1.0f, 1.0f)

    /**
     * Sets up the drawing object data for use in an OpenGL ES context.
     */
    init {
        // initialize vertex byte buffer for shape coordinates
        val bb = ByteBuffer.allocateDirect(
            // (# of coordinate values * 4 bytes per float)
            squareCoords.size * 4
        )
        bb.order(ByteOrder.nativeOrder())
        vertexBuffer = bb.asFloatBuffer()
        vertexBuffer.put(squareCoords)
        vertexBuffer.position(0)

        // initialize byte buffer for the draw list
        val dlb = ByteBuffer.allocateDirect(
            // (# of coordinate values * 2 bytes per short)
            drawOrder.size * 2
        )
        dlb.order(ByteOrder.nativeOrder())
        drawListBuffer = dlb.asShortBuffer()
        drawListBuffer.put(drawOrder)
        drawListBuffer.position(0)

        // prepare shaders and OpenGL program
        val vertexShader = MyGLRenderer.loadShader(
            GLES31.GL_VERTEX_SHADER,
            vertexShaderCode
        )
        val fragmentShader = MyGLRenderer.loadShader(
            GLES31.GL_FRAGMENT_SHADER,
            fragmentShaderCode
        )

        mProgram = GLES31.glCreateProgram()             // create empty OpenGL Program
        GLES31.glAttachShader(mProgram, vertexShader)   // add the vertex shader to program
        GLES31.glAttachShader(mProgram, fragmentShader) // add the fragment shader to program
        GLES31.glLinkProgram(mProgram)                  // create OpenGL program executables
    }

    /**
     * Encapsulates the OpenGL ES instructions for drawing this shape.
     *
     * @param mvpMatrix - The Model View Project matrix in which to draw
     * this shape.
     */
    fun draw(mvpMatrix: FloatArray) {
        // Add program to OpenGL environment
        GLES31.glUseProgram(mProgram)

        // get handle to vertex shader's vPosition member
        mPositionHandle = GLES31.glGetAttribLocation(mProgram, "vPosition")

        // Enable a handle to the triangle vertices
        GLES31.glEnableVertexAttribArray(mPositionHandle)

        // Prepare the triangle coordinate data
        GLES31.glVertexAttribPointer(
            mPositionHandle, COORDS_PER_VERTEX,
            GLES31.GL_FLOAT, false,
            vertexStride, vertexBuffer
        )

        // get handle to fragment shader's vColor member
        mColorHandle = GLES31.glGetUniformLocation(mProgram, "vColor")

        // Set color for drawing the triangle
        GLES31.glUniform4fv(mColorHandle, 1, color, 0)

        // get handle to shape's transformation matrix
        mMVPMatrixHandle = GLES31.glGetUniformLocation(mProgram, "uMVPMatrix")
        MyGLRenderer.checkGlError("glGetUniformLocation")

        // Apply the projection and view transformation
        GLES31.glUniformMatrix4fv(mMVPMatrixHandle, 1, false, mvpMatrix, 0)
        MyGLRenderer.checkGlError("glUniformMatrix4fv")

        // Draw the square
        GLES31.glDrawElements(
            GLES31.GL_TRIANGLES, drawOrder.size,
            GLES31.GL_UNSIGNED_SHORT, drawListBuffer
        )

        // Disable vertex array
        GLES31.glDisableVertexAttribArray(mPositionHandle)
    }

}