package model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

class TrainingData : ArrayList<TrainingData.TrainingDataItem>(){
    @Serializable
    data class TrainingDataItem(
        @SerialName("created_by")
        var createdBy: Any?, // null
        @SerialName("created_on")
        var createdOn: String?, // 2023-05-30 13:59:37.937211
        @SerialName("deleted_by")
        var deletedBy: Any?, // null
        @SerialName("deleted_on")
        var deletedOn: Any?, // null
        @SerialName("description")
        var description: String?, // Story of Taleemabad  How and why was it started? What does it mean to be a Taleemabad school  What's the long-term vision? What will a Taleemabad school look like in 20 years
        @SerialName("_id")
        var id: String?, // 6475bafa4f44ce91069f4dd9
        @SerialName("module")
        var module: String?, // f1f5edb9-1ecf-49ef-8868-6ed2e3dc9792
        @SerialName("questions")
        var questions: String?, // [{type=mcq, question={statement=Why do children often dislike Mathematics?, image_link=}, options=[{statement=Because it's difficult, image_link=}, {statement=Because its boring, image_link=}, {statement=Because they do not understand its real-life application, image_link=}, {statement=Because it is strange , image_link=}], answers=[3], hint=}, {type=mcq, question={statement=What is the approach to teaching Mathematics at Taleemabad?, image_link=}, options=[{statement=China's strong approach, image_link=}, {statement=Singapore’s real-life application approach , image_link=}, {statement=America’s freedom of study approach , image_link=}, {statement=Singapore’s meaningful education for all approach, image_link=}], answers=[2], hint=}, {type=MSQ, question={statement=What activities are used to teach children in Taleemabad school?, image_link=}, options=[{statement=Teacher Centered Activities, image_link=}, {statement=Student Centered Activities, image_link=}, {statement=Interactive games and quizzes, image_link=}, {statement=Animated videos and cartoons, image_link=}], answers=[2, 3, 4], hint=}, {type=open-ended, question={statement=What activities do you use to teach in your class? Name them , image_link=}, options=[], answers=[], hint=}, {type=open-ended, question={statement=What are the two things teachers need to teach Mathematics using a real-life application approach?, image_link=}, options=[], answers=[], hint=}, {type=mcq, question={statement=What is the long-term effect of disliking math?, image_link=}, options=[{statement=It has no effect on future career opportunities , image_link=}, {statement=It limits future career opportunities in math-related fields , image_link=}, {statement= It limits future career opportunities in all fields , image_link=}, {statement=It has a positive effect on future career opportunities , image_link=}], answers=[2], hint=}, {type=mcq, question={statement=Which of the following is an example of an activity that can help students understand fractions?, image_link=}, options=[{statement=Reading a textbook chapter on fractions, image_link=}, {statement=Watching a video about fractions, image_link=}, {statement=Playing a game that involves dividing objects into equal parts, image_link=}, {statement=Listening to a lecture on fractions, image_link=}], answers=[3], hint=}, {type=mcq, question={statement=Which of the following activities can help students develop their problem-solving skills in math?, image_link=}, options=[{statement=Memorizing formulas without understanding them, image_link=}, {statement=Copying solutions from a friend, image_link=}, {statement=Practicing a variety of challenging problems, image_link=}, {statement=Skipping math classes and studying on their own, image_link=}], answers=[3], hint=}, {type=mcq, question={statement=What is the philosophy of Taleemabad?, image_link=}, options=[{statement=To teach students only what they need to pass exams, image_link=}, {statement= To provide quality education to all students, regardless of their socioeconomic background, image_link=}, {statement=To focus only on academic learning and ignore the development of character and soft skills, image_link=}, {statement=To promote rote learning and memorization over critical thinking, image_link=}], answers=[2], hint=}, {type=open-ended, question={statement=How did you get to know about Taleemabad?, image_link=}, options=[], answers=[], hint=}]
        @SerialName("semester")
        var semester: String?, // fb4fae3e-3132-4388-a63e-a4b17f399530
        @SerialName("training")
        var training: String?, // c9b573b6-e60e-46d7-af0a-7a244525c8be
        @SerialName("updated_by")
        var updatedBy: Any?, // null
        @SerialName("updated_on")
        var updatedOn: Any?, // null
        @SerialName("uuid")
        var uuid: String?, // 130ae18a-1907-46cc-8246-d6525b2523fe
        @SerialName("video_link")
        var videoLink: String? // https://teacher-training-videos.s3.me-south-1.amazonaws.com/Taleemabad%27s+Philosophy.mp4
    )
}