import { GET_PROJECTS } from "../actions/types";

const initialState = {
  projects: [],
  project: {}
};

export default function(state = initialState, action) {
  switch (action.type) {
    case GET_PROJECTS:
      return {
        ...state,
        project: action.payload
      };
    default:
      return state;
  }
}
