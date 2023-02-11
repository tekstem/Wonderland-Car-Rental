import {createSlice} from "@reduxjs/toolkit";

const initialState = {
  error: "",
  success: "",
  isRegistering: false,
}

const registerSlice = createSlice({
  name: "register",
  initialState,
  reducers: {
    registrationPending: (state) => {
      state.isRegistering = true;
    },
    registrationSuccess: (state) => {
      state.error = "";
      state.success = true;
      state.isRegistering = false;
    },
    registrationFail: (state, {payload}) => {
      state.success = "";
      state.error = payload;
      state.isRegistering = false;
    }
  }
});

const {reducer, actions} = registerSlice;

export const {
  registrationFail,
  registrationPending,
  registrationSuccess,
} = actions;

export default reducer;
