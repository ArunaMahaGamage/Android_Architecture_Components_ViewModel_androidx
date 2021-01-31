# ViewModel_androidx
ViewModel is a class that is responsible for preparing and managing the data for an Activity or a Fragment. It also handles the communication of the Activity / Fragment with the rest of the application (e.g. calling the business logic classes).

A ViewModel is always created in association with a scope (an fragment or an activity) and will be retained as long as the scope is alive. E.g. if it is an Activity, until it is finished.

In other words, this means that a ViewModel will not be destroyed if its owner is destroyed for a configuration change (e.g. rotation). The new instance of the owner will just re-connected to the existing ViewModel.

The purpose of the ViewModel is to acquire and keep the information that is necessary for an Activity or a Fragment. The Activity or the Fragment should be able to observe changes in the ViewModel. ViewModels usually expose this information via LiveData or Android Data Binding. You can also use any observability construct from you favorite framework.

https://developer.android.com/reference/android/arch/lifecycle/ViewModel

# Add Below Class

        public class MainActivity extends AppCompatActivity {

		TextView tv_first_name, tv_first_name_new;
		Button btn_data;

		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_main);

			tv_first_name = findViewById(R.id.tv_first_name);
			tv_first_name_new = findViewById(R.id.tv_first_name_new);
			btn_data = findViewById(R.id.btn_data);

			UserViewModel viewModel = new ViewModelProvider(this).get(UserViewModel.class);

			viewModel.userLiveData.observe(this, new Observer<User>() {
				@Override
				public void onChanged(User user) {
					// update ui.
					tv_first_name.setText(user.getFirstName() + " " + user.getLastName());
				}
			});

			btn_data.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					viewModel.doAction();
					tv_first_name_new.setText("Portrait and landscape layout change");
				}
			});

			Log.e("MainActivity ", "hashCode_view_model:" + viewModel.hashCode());

			/*
			++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
					// In Fragment

					UserViewModel viewModel = new ViewModelProvider(requireActivity()).get(UserViewModel.class);

			++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
			*/
		}
	}
        
# Add Below XML

        <?xml version="1.0" encoding="utf-8"?>
        <androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
            xmlns:app="http://schemas.android.com/apk/res-auto"
            xmlns:tools="http://schemas.android.com/tools"
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            tools:context=".MainActivity">

            <TextView
                android:id="@+id/tv_first_name"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:text="Hello World!"
                app:layout_constraintBottom_toTopOf="@+id/tv_first_name_new"
                app:layout_constraintEnd_toEndOf="parent"
                app:layout_constraintHorizontal_bias="0.5"
                app:layout_constraintStart_toEndOf="@+id/textView2"
                app:layout_constraintTop_toTopOf="parent" />

            <TextView
                android:id="@+id/tv_first_name_new"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:text="TextView"
                app:layout_constraintBottom_toBottomOf="parent"
                app:layout_constraintEnd_toEndOf="parent"
                app:layout_constraintHorizontal_bias="0.5"
                app:layout_constraintStart_toEndOf="@+id/textView3"
                app:layout_constraintTop_toBottomOf="@+id/tv_first_name" />

            <TextView
                android:id="@+id/textView2"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:layout_marginStart="24dp"
                android:layout_marginLeft="24dp"
                android:text="First Name Old Pattern"
                app:layout_constraintBottom_toBottomOf="@+id/tv_first_name"
                app:layout_constraintEnd_toStartOf="@+id/tv_first_name"
                app:layout_constraintHorizontal_bias="0.5"
                app:layout_constraintStart_toStartOf="parent"
                app:layout_constraintTop_toTopOf="@+id/tv_first_name" />

            <TextView
                android:id="@+id/textView3"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:text="First Name New Pattern"
                app:layout_constraintBottom_toBottomOf="@+id/tv_first_name_new"
                app:layout_constraintEnd_toStartOf="@+id/tv_first_name_new"
                app:layout_constraintHorizontal_bias="0.5"
                app:layout_constraintStart_toStartOf="parent"
                app:layout_constraintTop_toTopOf="@+id/tv_first_name_new" />

        </androidx.constraintlayout.widget.ConstraintLayout>
        
# Add View Model Class

        import androidx.lifecycle.MutableLiveData;
        import androidx.lifecycle.ViewModel;

        public class UserViewModel extends ViewModel {

            public final MutableLiveData<User> userLiveData = new MutableLiveData<>();

            public UserViewModel() {
                // trigger user load.
                doAction();
            }

            void doAction() {
                // depending on the action, do necessary business logic calls and update the
                // userLiveData.
                // Do api Call
                User user = new User();
                user.setFirstName("Aruna");
                user.setLastName("Gamage");
                user.setAge(31);
                userLiveData.postValue(user);
            }

            void setUser(User user) {
                userLiveData.postValue(user);
            }
        }
# Add Model Class

        public class User {
            private String firstName;
            private String lastName;
            private int age;
            private double height;
            private double weight;

            public String getFirstName() {
                return firstName;
            }

            public void setFirstName(String firstName) {
                this.firstName = firstName;
            }

            public String getLastName() {
                return lastName;
            }

            public void setLastName(String lastName) {
                this.lastName = lastName;
            }

            public int getAge() {
                return age;
            }

            public void setAge(int age) {
                this.age = age;
            }

            public double getHeight() {
                return height;
            }

            public void setHeight(double height) {
                this.height = height;
            }

            public double getWeight() {
                return weight;
            }

            public void setWeight(double weight) {
                this.weight = weight;
            }
        }
