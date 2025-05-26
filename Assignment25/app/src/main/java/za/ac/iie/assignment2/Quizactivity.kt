package za.ac.iie.assignment2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Quizactivity : AppCompatActivity() {
    // Array of the flash card questions
    private val questions = arrayOf(
        "The Cradle of Humankind is UNESCO World Heritage Site located in Gauteng",
        "South Africa's first female president took office in 2005",
        "The first democratic election in South Africa was held in 1990",
        "Table Mountain is one of the New7Wonders of Nature",
        "The freedom Charter was adopted in Kliptown in 1995"
    )
    //Array storing the correct answers
    private val answers = booleanArrayOf(true, false, false, true, true)

    // Index to track which question the user is on
    private var questionsIndex = 0
    //variable to check users score
    private var score = 0

    private lateinit var txtQuestion: TextView
    private lateinit var txtFeedback: TextView
    private lateinit var btnTrue: Button
    private lateinit var btnFalse: Button
    private lateinit var btnNext: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_quizactivity)

        txtQuestion = findViewById(R.id.txtQuestion)
        txtFeedback = findViewById(R.id.txtFeedback)
        btnTrue = findViewById(R.id.btnTrue)
        btnFalse = findViewById(R.id.btnFalse)
        btnNext = findViewById(R.id.btnNext)

        //load the first question
        loadQuestion()

        // when the true button is clicked
        btnTrue.setOnClickListener {
            checkAnswer(true)
        }
        // when the false button is clicked
        btnFalse.setOnClickListener {
            checkAnswer(false)
        }
        //when the next button is clicked
        btnNext.setOnClickListener {
            questionsIndex++
            if (questionsIndex < questions.size) {
                //load the next question if there is more left
                loadQuestion()
            } else {
                // when the quiz is complete, go to the score activity and pass the score
                val intent = Intent(this, Scoreactivity::class.java)
                intent.putExtra("score",score)
                intent.putExtra("total",questions.size)
                startActivity(intent)
                finish()

            }

        }
    }
     
        // function to load current question on screen
        private fun loadQuestion() {
            txtQuestion.text = questions[questionsIndex]
            txtFeedback.text = "" // clear previous feedback
            btnTrue.isEnabled = true
            btnFalse.isEnabled = true
        }
              // function to check if users answer is correct or not
                private fun checkAnswer(userAnswer: Boolean) {
                    if (userAnswer == answers[questionsIndex]) {
                        txtFeedback.text = "Correct"
                        score++
                    } else {
                        txtFeedback.text = "Incorrect"
                    }
                  // disable answer buttons after selection
                    btnTrue.isEnabled = false
                    btnFalse.isEnabled = false

                }
                }

