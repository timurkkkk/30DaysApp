package com.example.a30daysapp

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.automirrored.rounded.ArrowForward
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.material.icons.rounded.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.coerceAtLeast
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.a30daysapp.ui.theme._30DaysAppTheme
import kotlin.math.min

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            _30DaysAppTheme {
                var currentPhoto by rememberSaveable { mutableStateOf(0) }
                val array = arrayOf(
                    PhotoCardClass(
                        painterResource(R.drawable.pic1),
                        "1. Устройте пикник в парке",
                        "Соберите корзину с любимыми закусками, фруктами и напитками, возьмите плед и отправляйтесь в ближайший парк. Наслаждайтесь природой, общением и вкусной едой на свежем воздухе."),
                    PhotoCardClass(
                        painterResource(R.drawable.pic2),
                        "2. Сходите на пляж",
                        "Проведите день у воды: купайтесь, загорайте, играйте в пляжный волейбол или стройте песчаные замки. Не забудьте взять с собой солнцезащитный крем и головной убор."),
                    PhotoCardClass(
                        painterResource(R.drawable.pic3),
                        "3. Организуйте велопрогулку",
                        "Исследуйте окрестности или прокатитесь по специальным велодорожкам. Это отличный способ совместить физическую активность и наслаждение природой."),
                    PhotoCardClass(
                        painterResource(R.drawable.pic4),
                        "4. Посадите цветы или зелень",
                        "Создайте мини-сад на балконе или в огороде. Это может быть петрушка, базилик или яркие цветы, которые будут радовать вас всё лето."),
                    PhotoCardClass(
                        painterResource(R.drawable.pic5),
                        "5. Устройте фотосессию на природе",
                        "Выберите красивое место и сделайте яркие летние фотографии. Это может быть поле, лес, городская набережная или даже ваш собственный двор."),
                    PhotoCardClass(
                        painterResource(R.drawable.pic6),
                        "6. Сходите в поход",
                        "Отправьтесь в лес или горы, наслаждайтесь природой и свежим воздухом. Возьмите с собой карту, компас и всё необходимое для комфортного отдыха."),
                    PhotoCardClass(
                        painterResource(R.drawable.pic7),
                        "7. Попробуйте новый вид спорта",
                        "Например, серфинг, SUP-бординг, бадминтон или теннис. Это отличный способ разнообразить летние дни и получить новые впечатления."),
                    PhotoCardClass(
                        painterResource(R.drawable.pic8),
                        "8. Соберите ягоды или фрукты",
                        "Сходите в лес за земляникой или на ферму за клубникой, вишней или яблоками. Свежие ягоды и фрукты станут отличным дополнением к вашему столу."),
                    PhotoCardClass(
                        painterResource(R.drawable.pic9),
                        "9. Устройте кинотеатр под открытым небом",
                        "Натяните простыню, включите проектор и смотрите фильм на свежем воздухе. Пригласите друзей и устройте уютный вечер с попкорном и напитками."),
                    PhotoCardClass(
                        painterResource(R.drawable.pic10),
                        "10. Сделайте мороженое своими руками",
                        "Приготовьте домашнее мороженое или фруктовый сорбет. Это будет не только вкусно, но и полезно, ведь вы сами контролируете ингредиенты."),
                    PhotoCardClass(
                        painterResource(R.drawable.pic11),
                        "11. Сходите на фестиваль или концерт",
                        "Летом часто проходят музыкальные фестивали, ярмарки и культурные мероприятия. Проведите время в атмосфере праздника и веселья."),
                    PhotoCardClass(
                        painterResource(R.drawable.pic12),
                        "12. Устройте день настольных игр",
                        "Соберите друзей или семью и поиграйте в любимые настольные игры. Это отличный способ провести время вместе и посоревноваться в дружеской атмосфере."),
                    PhotoCardClass(
                        painterResource(R.drawable.pic13),
                        "13. Сходите в зоопарк или на ферму",
                        "Познакомьтесь с животными и проведите время на природе. Это особенно понравится детям, но и взрослые получат массу удовольствия."),
                    PhotoCardClass(
                        painterResource(R.drawable.pic14),
                        "14. Научитесь готовить новое блюдо",
                        "Например, паэлью, тако или летний салат. Экспериментируйте с рецептами и удивляйте своих близких кулинарными шедеврами."),
                    PhotoCardClass(
                        painterResource(R.drawable.pic15),
                        "15. Устройте день без гаджетов",
                        "Проведите время на природе, читайте книги или общайтесь с близкими. Это поможет отдохнуть от цифрового мира и насладиться настоящим моментом."),
                    PhotoCardClass(
                        painterResource(R.drawable.pic16),
                        "16. Сходите в музей или на выставку",
                        "Узнайте что-то новое и вдохновитесь искусством. Летом многие музеи предлагают специальные программы и экскурсии."),
                    PhotoCardClass(
                        painterResource(R.drawable.pic17),
                        "17. Устройте вечерний костер",
                        "Приготовьте зефир, спойте песни под гитару и наслаждайтесь атмосферой. Это идеальный способ завершить день на природе."),
                    PhotoCardClass(
                        painterResource(R.drawable.pic18),
                        "18. Сделайте скворечник или кормушку",
                        "Помогите птицам и украсьте свой сад. Это не только полезно, но и увлекательно, особенно если делать это вместе с детьми."),
                    PhotoCardClass(
                        painterResource(R.drawable.pic19),
                        "19. Сходите в аквапарк",
                        "Проведите день, катаясь на водных горках и купаясь в бассейнах. Это отличный способ охладиться в жаркий день и получить массу положительных эмоций."),
                    PhotoCardClass(
                        painterResource(R.drawable.pic20),
                        "20. Устройте день творчества",
                        "Рисуйте, лепите, делайте поделки или украшения. Это поможет раскрыть ваш творческий потенциал и создать что-то уникальное."),
                    PhotoCardClass(
                        painterResource(R.drawable.pic21),
                        "21. Сходите на рыбалку",
                        "Наслаждайтесь тишиной и природой, а потом приготовьте улов на гриле. Это отличный способ расслабиться и насладиться спокойствием."),
                    PhotoCardClass(
                        painterResource(R.drawable.pic22),
                        "22. Устройте день наблюдения за звёздами",
                        "Отправьтесь за город, где нет городских огней, и возьмите с собой телескоп или бинокль. Наслаждайтесь красотой ночного неба, ищите созвездия и наблюдайте за падающими звёздами."),
                    PhotoCardClass(
                        painterResource(R.drawable.pic23),
                        "23. Сходите в ботанический сад",
                        "Полюбуйтесь красивыми растениями и цветами. Это отличное место для прогулок и вдохновения."),
                    PhotoCardClass(
                        painterResource(R.drawable.pic24),
                        "24. Устройте день чтения",
                        "Выберите книгу, найдите уютное место и погрузитесь в чтение. Лето — идеальное время, чтобы наконец прочитать то, что давно откладывали."),
                    PhotoCardClass(
                        painterResource(R.drawable.pic25),
                        "25. Сделайте летний коктейль",
                        "Приготовьте мохито, лимонад или смузи из свежих фруктов. Это освежит в жаркий день и станет отличным дополнением к вашему отдыху."),
                    PhotoCardClass(
                        painterResource(R.drawable.pic26),
                        "26. Сходите в поход с палатками",
                        "Проведите ночь под звёздами, готовьте еду на костре и наслаждайтесь природой. Это приключение запомнится надолго."),
                    PhotoCardClass(
                        painterResource(R.drawable.pic27),
                        "27. Сходите в парк развлечений",
                        "Проведите день, катаясь на аттракционах, пробуя сладкую вату и наслаждаясь атмосферой праздника. Это отличный способ зарядиться энергией и получить массу положительных эмоций!"),
                    PhotoCardClass(
                        painterResource(R.drawable.pic28),
                        "28. Сходите на экскурсию",
                        "Исследуйте новые места в своём городе или за его пределами. Узнайте больше о истории и культуре вашего региона."),
                    PhotoCardClass(
                        painterResource(R.drawable.pic29),
                        "29. Устройте день спорта",
                        "Сыграйте в футбол, баскетбол или бадминтон с друзьями. Это отличный способ поддерживать форму и весело провести время."),
                    PhotoCardClass(
                        painterResource(R.drawable.pic30),
                        "30. Сделайте летний коллаж или альбом",
                        "Соберите фотографии, билеты и воспоминания в один альбом, чтобы сохранить их на долгие годы. Это будет ваша личная летняя история."),
                )

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    LazyColumn(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .padding(8.dp)

                    ) {
                        items (1) {
                            if (LocalConfiguration.current.orientation == Configuration.ORIENTATION_PORTRAIT)
                                Spacer(modifier = Modifier.height(52.dp))
                            else
                                Spacer(modifier = Modifier.height(32.dp))
                            Header("30 Идей для летних активностей")
                            Spacer(modifier = Modifier.height(8.dp))

                        }
                        items(array.size) { index ->
                            PhotoCard(array[index])
                            Spacer(modifier = Modifier.height(8.dp))
                        }
                        items (1) {
                            Spacer(modifier = Modifier.height(32.dp))
                        }

                    }

                }
            }
        }
    }
}

@Composable
fun Header(header: String, modifier: Modifier = Modifier) {
    Text(
        text = header,
        modifier = modifier,
        color = MaterialTheme.colorScheme.primary,
        fontSize = 30.sp,
        textAlign = TextAlign.Center,
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    _30DaysAppTheme {
        Header("Android")
    }
}

class PhotoCardClass {
    constructor(image: Painter, name : String = "", description : String = "")
    {
        this.image = image
        this.name = name
        this.description = description
    }
    var image : Painter
    var name : String
    var description : String
}

@Composable
fun PhotoCard(photoCard : PhotoCardClass) {
    var expanded by rememberSaveable { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .padding(16.dp)
            .clickable { expanded = !expanded }
            .widthIn(min = (
                    if (LocalConfiguration.current.orientation == Configuration.ORIENTATION_PORTRAIT) Dp.Unspecified
                    else (LocalConfiguration.current.screenWidthDp * 0.7).dp))
            .width(IntrinsicSize.Min)
        ,
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        ),
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.CenterHorizontally)
                .widthIn(max = (
                        if (LocalConfiguration.current.orientation == Configuration.ORIENTATION_PORTRAIT) Dp.Unspecified
                        else (LocalConfiguration.current.screenWidthDp * 0.6).dp))

        ) {
                Image(
                    photoCard.image,
                    contentDescription = null,

                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = photoCard.name,
                    color = MaterialTheme.colorScheme.primary,
                    textAlign = TextAlign.Center,
                )
                Spacer(modifier = Modifier.height(8.dp))
            if (expanded) {
                Icon(Icons.Rounded.KeyboardArrowUp, contentDescription = null)
                Text(
                    text = photoCard.description,
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.align(Alignment.Start)
                        .padding(16.dp)
                        .fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
            }
            else {
                Icon(Icons.Rounded.KeyboardArrowDown, contentDescription = null)
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}