package com.example.workcollect.database;

import com.example.workcollect.ui.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.WriteBatch;

public class TaskRepository {
    private final FirebaseFirestore database = FirebaseFirestore.getInstance();

    public interface Callback {
        void onSuccess();
        void onError(Exception e);
    }

    public void addTask(String strGroupId, Task task, Callback callback){
        task.setStrId(
                database.collection(DatabaseCollections.GROUPS.getStrValue())
                        .document(strGroupId)
                        .collection(DatabaseCollections.TASKS.getStrValue())
                        .document().getId()
        );

        WriteBatch writeBatch = database.batch();
        DocumentReference taskReference = database.collection(
                    DatabaseCollections.GROUPS.getStrValue())
                .document(strGroupId).collection(
                        DatabaseCollections.TASKS.getStrValue())
                .document(task.getStrId());
        writeBatch.set(taskReference, task);

        writeBatch.commit().addOnSuccessListener(v -> callback.onSuccess())
                .addOnFailureListener(callback::onError);
    }
}
