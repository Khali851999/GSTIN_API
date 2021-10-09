import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_add_contacts.*
import kotlinx.android.synthetic.main.activity_contacts.*
import kotlinx.android.synthetic.main.activity_edit_contacts.*

class AddContactsActivity : AppCompatActivity() {


    private var firebaseAuth = FirebaseAuth.getInstance()
    private val firebaseFirestore = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_contacts)

        this.title = "Add Contact"

        btnAddContact.setOnClickListener {
            val contactName = etContactName.text.toString()
            val contactDesig = etContactDesignation.text.toString()
            val contactPhone = etContactPhone.text.toString()
            val contactEmail = etContactEmail.text.toString()

            if (contactName.isEmpty()) {
                Toast.makeText(
                    applicationContext,
                    "Please Enter the Name",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }
            if (contactDesig.isEmpty()) {
                Toast.makeText(
                    applicationContext,
                    "Please Enter the Designation",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }
            if (contactEmail.isEmpty() && contactPhone.isEmpty()) {
                Toast.makeText(
                    applicationContext,
                    "Please Enter the Email",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }
            showVerifyAlertDialogue()

        }

    }

    private fun showVerifyAlertDialogue() {
        val alertDialog = AlertDialog.Builder(this)
        alertDialog.apply {
            setTitle("Save Changes...")
            setMessage("Do you want to upload the contact?")
            setPositiveButton("Save") { _, _ ->

                val contactName = etContactName.text.toString()
                val contactDesig = etContactDesignation.text.toString()
                val contactPhone = etContactPhone.text.toString()
                val contactEmail = etContactEmail.text.toString()

                val uId = firebaseAuth.currentUser.uid

                var college: String

                val docRef = firebaseFirestore.collection("Profiles").document(uId)
                docRef.get().addOnSuccessListener { documentSnapshot ->
                    college = documentSnapshot.getString("college").toString()
                    firebaseFirestore.collection("Contacts").document(college)
                        .collection("Contact")


                    val contactDetail = HashMap<String, Any>()

                    contactDetail["name"] = contactName
                    contactDetail["designation"] = contactDesig
                    contactDetail["email"] = contactEmail
                    contactDetail["phone"] = contactPhone

                    Firebase.firestore.collection("Contacts")
                        .document(college).collection("Contact").document(contactDesig)
                        .set(contactDetail)

                    Toast.makeText(
                        applicationContext,
                        "Contact Added",
                        Toast.LENGTH_SHORT
                    ).show()

                    val intent = Intent(applicationContext, ContactsActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }
            setNegativeButton("Cancel") { _, _ -> }
        }
        alertDialog.show()
    }
}
