package sagar.svgassignment.generateDogs

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import sagar.svgassignment.home.HeaderContent
import sagar.svgassignment.ui.theme.ButtonColor
import sagar.svgassignment.ui.theme.SVGAssignmentTheme

class GenerateDogsActivity: ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val generateDogsViewModel = GenerateDogsViewModel()
        enableEdgeToEdge()
        setContent {
            SVGAssignmentTheme {
                Column (
                     modifier = Modifier
                         .fillMaxSize(),
                    verticalArrangement = Arrangement.Top
                ){
                    HeaderContent("Generate Dogs!")
                    Spacer(modifier = Modifier.height(16.dp))
                    GenerateDogs(generateDogsViewModel)
                }
            }
        }
    }

}

@Composable
fun GenerateDogs(generateDogsViewModel: GenerateDogsViewModel) {
    val imageUrl by generateDogsViewModel.imageUrl.observeAsState("")
    val enableButton by generateDogsViewModel.enableButton.observeAsState(true)
    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        val painter = rememberAsyncImagePainter(imageUrl)
        Image(
            painter = painter,
            contentDescription = "Generated image",
            modifier = Modifier
                .size(250.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                generateDogsViewModel.generateDogImage()
            },
            enabled = enableButton,
            colors = ButtonDefaults.buttonColors(
                containerColor = ButtonColor
            ),
            modifier = Modifier
                .padding(10.dp),
        ) {
            Text(
                text = "Generate!",
                fontSize = 16.sp)
        }
    }
}