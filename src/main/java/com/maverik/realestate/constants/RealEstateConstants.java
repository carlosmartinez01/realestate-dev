package com.maverik.realestate.constants;

public class RealEstateConstants {

    public enum UserInfo {
	ACTIVE {
	    @Override
	    public String toString() {
		return "0";
	    }
	},
	BOOL_ACTIVE {
	    @Override
	    public String toString() {
		return "True";
	    }
	},
	INACTIVE {
	    @Override
	    public String toString() {
		return "1";
	    }
	}
    }

    public enum Actions {
	ADD {
	    @Override
	    public String toString() {
		return "Add";
	    }
	},
	UPDATE {
	    @Override
	    public String toString() {
		return "Update";
	    }
	},
	PAGE_BACK {
	    @Override
	    public String toString() {
		return "Back";
	    }
	},
	PAGE_ADD {
	    @Override
	    public String toString() {
		return "Add";
	    }
	},
	MOVE {
	    @Override
	    public String toString() {
		return "move";
	    }
	},
	SAVE {
	    @Override
	    public String toString() {
		return "save";
	    }
	},
	ACCEPT {
	    @Override
	    public String toString() {
		return "accept";
	    }
	}
    }

    public enum Roles {
	USER {
	    @Override
	    public String toString() {
		return "ROLE_USER";
	    }
	},
	ADMIN {
	    @Override
	    public String toString() {
		return "ROLE_ADMINISTRATOR";
	    }
	}
    }

    public enum ObjectType {
	PROPERTY {
	    @Override
	    public String toString() {
		return "property";
	    }
	},
	COMPANY {
	    @Override
	    public String toString() {
		return "company";
	    }
	},
	PROJECT {
	    @Override
	    public String toString() {
		return "project";
	    }
	}
    }

    public enum Strings {
	NOT_EMPTY {
	    @Override
	    public String toString() {
		return " ";
	    }
	}
    }

    public enum RequestParams {
	USER_FULLNAME {
	    @Override
	    public String toString() {
		return "userFullName";
	    }
	},
	ACTION {
	    @Override
	    public String toString() {
		return "action";
	    }
	},
	ACTIVE {
	    @Override
	    public String toString() {
		return "active";
	    }
	},
	PASSWORD {
	    @Override
	    public String toString() {
		return "password";
	    }
	},
	PROPERTY_ID {
	    @Override
	    public String toString() {
		return "propertyOID";
	    }
	},
	USERNAME {
	    @Override
	    public String toString() {
		return "username";
	    }
	}
    }

    public enum Meetings {
	TOTAL_MEETINGS {
	    @Override
	    public String toString() {
		return "9";
	    }
	}
    }
}
