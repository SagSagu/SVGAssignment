package sagar.svgassignment.displayDogs

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import sagar.svgassignment.generateDogs.GenerateDogsViewModel
import sagar.svgassignment.home.HeaderContent
import sagar.svgassignment.ui.theme.ButtonColor
import sagar.svgassignment.ui.theme.SVGAssignmentTheme
import sagar.svgassignment.utils.LocalCache

class DisplayDogsActivity: ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SVGAssignmentTheme {
                Column (
                    modifier = Modifier
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.Top
                ){
                    HeaderContent("My Recently Generated Dogs!")
                    Spacer(modifier = Modifier.height(16.dp))
                    DisplayDogs()
                }
            }
        }
    }

}

@Composable
fun DisplayDogs() {
    Column (
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        ImageList()

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                LocalCache.clearCache()
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = ButtonColor
            ),
            modifier = Modifier
                .padding(10.dp),
        ) {
            Text(
                text = "Clear Dogs!",
                fontSize = 16.sp)
        }
    }
}

@Composable
fun ImageList() {
    val images by LocalCache.localList.observeAsState()
    LazyRow (
        modifier = Modifier.fillMaxWidth(),
    ) {
        if (images != null) {
            items(images!!.size) { index ->
                ImageCard(images!![index])
            }
        }
    }
}

@Composable
fun ImageCard(imageUrl: String) {
    val painter = rememberAsyncImagePainter(imageUrl)
    Column(
        modifier = Modifier
            .width(250.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painter,
            contentDescription = "Dog Image",
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
                .padding(5.dp),
            contentScale = ContentScale.Crop,
        )
    }
}